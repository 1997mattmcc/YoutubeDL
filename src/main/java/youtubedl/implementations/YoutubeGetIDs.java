package youtubedl.implementations;

import java.util.function.Consumer;
import youtubedl.YoutubeDLBuilder;
import youtubedl.Properties;
import java.util.ArrayList;
import java.util.List;

public class YoutubeGetIDs extends YoutubeDLBuilder {

    private final YoutubeDLBuilder builder = new YoutubeDLBuilder();
    private final List<String> queued = new ArrayList<>();
    private boolean downloading = false;

    public YoutubeGetIDs(Consumer<String> idListener) {
        builder.termination(consoleLog -> this.termination(consoleLog));
        builder.property(Properties.YOUTUBE_SKIP_DASH_MANIFEST);
        builder.property(Properties.IGNORE_ERRORS);
        builder.property(Properties.GET_ID);
        builder.console(idListener);
    }

    public void getIDs(List<String> urls) {
        queued.addAll(urls);
        if (!downloading) {
            new Thread(builder.setUrls(queued).build()).start();
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
