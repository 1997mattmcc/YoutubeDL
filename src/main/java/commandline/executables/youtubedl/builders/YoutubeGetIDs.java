package commandline.executables.youtubedl.builders;

import commandline.executables.youtubedl.YoutubeDL.Properties;
import commandline.executables.youtubedl.YoutubeDLBuilder;

public class YoutubeGetIDs extends YoutubeDLBuilder {

    public YoutubeGetIDs() {
        super.setConsoleFilter(consoleLine -> this.filter(consoleLine));

        super.property(Properties.YOUTUBE_SKIP_DASH_MANIFEST);
        super.property(Properties.IGNORE_ERRORS);
        super.property(Properties.GET_ID);
    }

    private String filter(String consoleLine) {
        if (consoleLine.length() == 11) {
            return consoleLine;
        }
        return new String();
    }
}
