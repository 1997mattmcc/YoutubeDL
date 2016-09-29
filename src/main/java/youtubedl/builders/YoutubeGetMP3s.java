package youtubedl.builders;

import org.apache.commons.io.FilenameUtils;
import java.util.function.Consumer;
import youtubedl.YoutubeDLBuilder;
import youtubedl.Properties;
import java.util.ArrayList;
import youtubedl.Options;
import java.util.List;
import java.io.File;

public class YoutubeGetMP3s extends YoutubeDLBuilder {

    private final YoutubeGetIDs idGetter = new YoutubeGetIDs(id -> this.download(id));
    private final List<String> pending = new ArrayList<>();
    private final List<String> queued = new ArrayList<>();
    private final Consumer<File> fileConsumer;
    private final String destination;
    private boolean downloading = false;

    public YoutubeGetMP3s(String destination, Consumer<File> fileConsumer) {
        this.fileConsumer = fileConsumer;
        this.destination = destination;

        super.option(Options.DOWNLOAD_ARCHIVE, destination.concat("archive.txt"));
        super.option(Options.OUTPUT, destination.concat("%(id)s.%(ext)s"));
        super.option(Options.AUDIO_QUALITY, "140");
        super.option(Options.AUDIO_FORMAT, "mp3");
        super.option(Options.EXEC, "\"echo {}\"");

        super.property(Properties.YOUTUBE_SKIP_DASH_MANIFEST);
        super.property(Properties.WRITE_INFO_JSON);
        super.property(Properties.IGNORE_ERRORS);
        super.property(Properties.EXTRACT_AUDIO);
        super.property(Properties.QUIET);

        super.termination(consoleLog -> this.termination(consoleLog));
        super.console(consoleLine -> this.consoleLogged(consoleLine));
    }

    public void getMP3s(List<String> urls) {
        idGetter.getIDs(urls);
    }

    private void download(String id) {
        if (!id.isEmpty()) {
            queued.add(id);
        }
        if (!downloading) {
            downloading = true;
            pending.addAll(queued);
            queued.clear();
            new Thread(super.setUrls(pending).build()).start();
        }
    }

    private void consoleLogged(String consoleLog) {
        File file = new File(consoleLog.replaceAll("'", ""));
        pending.remove(FilenameUtils.removeExtension(file.getName()));
        fileConsumer.accept(file);
    }

    private void termination(List<String> consoleLog) {
        pending.stream().forEach((id) -> fileConsumer.accept(new File(String.format("%s%s.mp3", destination, id))));
        pending.clear();
        downloading = false;
        if (!queued.isEmpty()) {
            this.download(new String());
        }
    }
}
