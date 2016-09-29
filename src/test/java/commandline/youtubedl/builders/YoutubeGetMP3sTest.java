package commandline.youtubedl.builders;

import commandline.youtubedl.builders.YoutubeGetMP3s;
import commandline.youtubedl.YoutubeDL;
import java.util.Arrays;

public class YoutubeGetMP3sTest {

    public static void main(String[] args) {

        YoutubeDL.setExecutable("resources/youtube-dl.exe");
        YoutubeGetMP3s youtubeGetMP3s = new YoutubeGetMP3s("resources/youtube/", file -> System.out.println(file.getPath()));
        youtubeGetMP3s.getMP3s(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
        youtubeGetMP3s.getMP3s(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
        youtubeGetMP3s.getMP3s(Arrays.asList("https://www.youtube.com/watch?v=jNQXAC9IVRw"));
    }
}
