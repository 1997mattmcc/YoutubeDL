package commandline.executables.youtubedl.builders;

import commandline.executables.youtubedl.YoutubeDL.Properties;
import commandline.executables.youtubedl.YoutubeDL.Options;
import commandline.executables.youtubedl.YoutubeDLBuilder;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

public class YoutubeGetMP3s extends YoutubeDLBuilder {

    public YoutubeGetMP3s(String destination) {
        super.setConsoleFilter(consoleLine -> this.filter(consoleLine));

        super.option(Options.DOWNLOAD_ARCHIVE, destination.concat("archive.txt"));
        super.option(Options.OUTPUT, destination.concat("%(id)s.%(ext)s"));
        super.option(Options.AUDIO_QUALITY, "140");
        super.option(Options.AUDIO_FORMAT, "mp3");
        super.option(Options.EXEC, "\"echo {}\"");

        super.property(Properties.YOUTUBE_SKIP_DASH_MANIFEST);
        super.property(Properties.IGNORE_ERRORS);
        super.property(Properties.EXTRACT_AUDIO);
        super.property(Properties.QUIET);
    }

    private String filter(String consoleLine) {
        File file = new File(consoleLine.replaceAll("'", ""));
        if (file.isFile()) {
            return FilenameUtils.removeExtension(file.getName());
        }
        return new String();
    }
}
