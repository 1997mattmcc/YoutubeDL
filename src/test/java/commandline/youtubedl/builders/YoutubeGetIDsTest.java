package commandline.youtubedl.builders;

import commandline.youtubedl.builders.YoutubeGetIDs;
import commandline.youtubedl.YoutubeDL;
import java.util.Arrays;

public class YoutubeGetIDsTest {

    public static void main(String[] args) {

        YoutubeDL.setExecutable("resources/youtube-dl.exe");
        YoutubeGetIDs youtubeGetIDs = new YoutubeGetIDs(YoutubeDL.DEFAULT_CONSOLE_LOGGER);
        youtubeGetIDs.getIDs(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
        youtubeGetIDs.getIDs(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
        youtubeGetIDs.getIDs(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
    }
}
