package youtubedl;

import youtubedl.YoutubeDL.YoutubeDLBuilder;

public class YoutubeDLTest {

    public static void main(String[] args) {

        //YOUTUBE-DL BUILDER
        YoutubeDLBuilder youtubeBuilder = new YoutubeDLBuilder(); // Creates a youtube-dl builder
        youtubeBuilder.consoleLogger(line -> System.out.println(line)); // Adds a console listener
        youtubeBuilder.addOption(Options.OUTPUT, "resources/%(id)s.%(ext)s"); // Adds an option
        youtubeBuilder.withTerminationLogger(lines -> System.out.println(String.format("youtube-dl download complete with (%s) console lines", lines.size()))); // Adds a termination logger
        youtubeBuilder.downloader("resources/youtube-dl.exe"); // Sets the downloader exe (windows)
        youtubeBuilder.withURL("https://www.youtube.com/watch?v=jNQXAC9IVRw"); // Adds a url to download
        YoutubeDL youtube = youtubeBuilder.build(); // Builds a YoutubeDL object
        new Thread(youtube).start(); // Runs the downloader in a new thread
    }
}
