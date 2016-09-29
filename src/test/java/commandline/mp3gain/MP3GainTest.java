package commandline.mp3gain;

public class MP3GainTest {

    public static void main(String[] args) {

        //MP3GAIN CONFIGURE
        MP3Gain.setExecutable("resources/mp3gain.exe");                              // Sets the executable (windows)

        //MP3GAIN BUILDER
        MP3GainBuilder mp3gainBuilder = new MP3GainBuilder();                        // Creates a mp3gain builder

        mp3gainBuilder.setTerminationLogger(MP3Gain.DEFAULT_TERMINATION_LOGGER);     // Sets the termination logger
        mp3gainBuilder.setInitiationLogger(MP3Gain.DEFAULT_INITIATION_LOGGER);       // Sets the termination logger
        mp3gainBuilder.setConsoleLogger(MP3Gain.DEFAULT_CONSOLE_LOGGER);             // Sets the console listener

        mp3gainBuilder.property(Properties.AUTO_APPLY_TRACK_GAIN);                   // Adds a property
        mp3gainBuilder.property(Properties.IGNORE_CLIPPING);                         // Adds a property
        mp3gainBuilder.file("resources/youtube/jNQXAC9IVRw.mp3");                    // Adds a mp3 file

        //MP3GAIN EXECUTION
        MP3Gain mp3gain = mp3gainBuilder.build();                                    // Builds a MP3Gain object
        new Thread(mp3gain).start();                                                 // Runs the command in a new thread
    }
}
