package commandline;

import commandline.executables.mp3gain.MP3Gain.Properties;
import commandline.executables.mp3gain.MP3GainBuilder;
import commandline.executables.mp3gain.MP3Gain;

public class MP3GainTest {

    public static void main(String[] args) {

        //MP3GAIN CONFIGURE
        MP3Gain.setExecutable("resources/mp3gain.exe");               // Sets the executable

        //MP3GAIN BUILDER
        MP3GainBuilder builder = new MP3GainBuilder();                // Creates the mp3gain builder
        builder.setTermination(Command.DEFAULT_TERMINATION_LOGGER);   // Sets the termination logger
        builder.setInitiation(Command.DEFAULT_INITIATION_LOGGER);     // Sets the termination logger
        builder.setConsole(Command.DEFAULT_CONSOLE_LOGGER);           // Sets the console listener

        builder.property(Properties.AUTO_APPLY_TRACK_GAIN);           // Adds a property
        builder.property(Properties.IGNORE_CLIPPING);                 // Adds a property
        builder.property(Properties.QUIET);                           // Adds a property
        builder.file("Some File");                                    // Adds a file to edit

        //MP3GAIN EXECUTION
        Command mp3gain = builder.build();                            // Builds a Command object
        new CommandLine().queueCommand(mp3gain);                      // Runs the downloader in a new thread
    }
}
