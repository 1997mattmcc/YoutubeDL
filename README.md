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
        YoutubeDL.setExecutable("resources/youtube-dl.exe");                         // Sets the executable (windows)

        //YOUTUBE-DL BUILDER
        YoutubeDLBuilder youtubeDLBuilder = new YoutubeDLBuilder();                  // Creates a youtube-dl builder

        youtubeDLBuilder.setTerminationLogger(YoutubeDL.DEFAULT_TERMINATION_LOGGER); // Sets the termination logger
        youtubeDLBuilder.setInitiationLogger(YoutubeDL.DEFAULT_INITIATION_LOGGER);   // Sets the termination logger
        youtubeDLBuilder.setConsoleLogger(YoutubeDL.DEFAULT_CONSOLE_LOGGER);         // Sets the console listener

        youtubeDLBuilder.option(Options.OUTPUT, "resources/%(id)s.%(ext)s");         // Adds an option
        youtubeDLBuilder.url("https://www.youtube.com/watch?v=jNQXAC9IVRw");         // Adds a url to download

        //YOUTUBE-DL EXECUTION
        YoutubeDL youtubeDL = youtubeDLBuilder.build();                              // Builds a YoutubeDL object
        new Thread(youtubeDL).start();                                               // Runs the downloader in a new thread
    }
}
```

### MP3Gain
```java
public class Example {
    
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
```