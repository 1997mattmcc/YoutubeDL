package commandline.executables.mp3gain.builders;

import commandline.executables.mp3gain.MP3Gain.Properties;
import commandline.executables.mp3gain.MP3GainBuilder;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

public class NormalizeMP3Default extends MP3GainBuilder {

    public NormalizeMP3Default() {
        // super.setConsoleFilter(consoleLine -> this.filter(consoleLine));

        super.property(Properties.AUTO_APPLY_TRACK_GAIN);
        super.property(Properties.IGNORE_CLIPPING);
        super.property(Properties.QUIET);
    }

    private String filter(String consoleLine) {
        System.out.println(">: " + consoleLine);
        File file = new File(consoleLine);
        if (file.isFile()) {
            System.out.println("GOTTED " + consoleLine);
            return FilenameUtils.removeExtension(file.getName());
        }
        return new String();
    }
}
