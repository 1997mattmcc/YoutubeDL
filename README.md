# YoutubeDL
A youtube-dl wrapper for java

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.1997mattmcc</groupId>
        <artifactId>YoutubeDL</artifactId>
        <version>@VERSION@</version>
    </dependency>
</dependencies>
```

### YoutubeDL
```java
public class Example {
    
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
```

### MP3Gain
```java
public class Example {
    
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
```