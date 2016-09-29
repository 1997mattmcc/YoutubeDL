package commandline.mp3gain.builders;

import commandline.mp3gain.MP3Gain;
import java.util.Arrays;

public class NormalizeMP3DefaultTest {

    public static void main(String[] args) {

        MP3Gain.setExecutable("resources/mp3gain.exe");
        NormalizeMP3Default normalizeMP3s = new NormalizeMP3Default(MP3Gain.DEFAULT_CONSOLE_LOGGER);
        normalizeMP3s.normalize(Arrays.asList("resources/youtube/jNQXAC9IVRw.mp3"));
    }
}
