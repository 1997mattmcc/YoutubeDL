package commandline;

import commandline.executables.youtubedl.YoutubeDL.Options;
import commandline.executables.youtubedl.YoutubeDLBuilder;
import commandline.executables.youtubedl.YoutubeDL;

public class YoutubeDLTest {

    public static void main(String[] args) {

        //YOUTUBE-DL CONFIGURE
        YoutubeDL.setExecutable("resources/youtube-dl.exe");          // Sets the executable

        //YOUTUBE-DL BUILDER
        YoutubeDLBuilder builder = new YoutubeDLBuilder();            // Creates a youtube-dl builder
        builder.setTermination(Command.DEFAULT_TERMINATION_LOGGER);   // Sets the termination logger
        builder.setInitiation(Command.DEFAULT_INITIATION_LOGGER);     // Sets the termination logger
        builder.setConsole(Command.DEFAULT_CONSOLE_LOGGER);           // Sets the console listener

        builder.option(Options.OUTPUT, "resources/%(id)s.%(ext)s");   // Adds an option
        builder.url("Some URL");                                      // Adds a url to download

        //YOUTUBE-DL EXECUTION
        Command youtubeDL = builder.build();                          // Builds a Command object
        new CommandLine().queueCommand(youtubeDL);                    // Runs the downloader in a new thread
    }
}
