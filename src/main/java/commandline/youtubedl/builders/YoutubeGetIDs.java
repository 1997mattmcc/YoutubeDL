package commandline.youtubedl.builders;

import java.util.function.Consumer;
import commandline.youtubedl.YoutubeDLBuilder;
import commandline.youtubedl.Properties;
import java.util.ArrayList;
import java.util.List;

public class YoutubeGetIDs extends YoutubeDLBuilder {

    private final List<String> queued = new ArrayList<>();
    private boolean downloading = false;

    public YoutubeGetIDs(Consumer<String> idListener) {
        super.termination(consoleLog -> this.termination(consoleLog));
        super.property(Properties.YOUTUBE_SKIP_DASH_MANIFEST);
        super.property(Properties.IGNORE_ERRORS);
        super.property(Properties.GET_ID);
        super.console(idListener);
    }

    public void getIDs(List<String> urls) {
        queued.addAll(urls);
        if (!downloading) {
            downloading = true;
            new Thread(super.setUrls(queued).build()).start();
            queued.clear();
        }
    }

    private void termination(List<String> consoleLog) {
        downloading = false;
        if (!queued.isEmpty()) {
            this.getIDs(new ArrayList<>());
        }
    }
}
