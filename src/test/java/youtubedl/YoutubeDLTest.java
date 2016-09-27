package youtubedl;

import youtubedl.YoutubeDL.YoutubeDLBuilder;

public class YoutubeDLTest {

    public static void main(String[] args) {

        //YOUTUBE-DL BUILDER
        YoutubeDLBuilder youtubeDLBuilder = new YoutubeDLBuilder(); // Creates a youtube-dl builder
        youtubeDLBuilder.consoleLogger(line -> System.out.println(line)); // Sets the console listener
        youtubeDLBuilder.terminationLogger(lines -> System.out.println(String.format("youtube-dl download complete with (%s) console lines", lines.size()))); // Sets the termination logger
        youtubeDLBuilder.addOption(Options.OUTPUT, "resources/%(id)s.%(ext)s"); // Adds an option
        youtubeDLBuilder.downloader("resources/youtube-dl.exe"); // Sets the downloader exe (windows)
        youtubeDLBuilder.addURL("https://www.youtube.com/watch?v=jNQXAC9IVRw"); // Adds a url to download
        YoutubeDL youtubeDL = youtubeDLBuilder.build(); // Builds a YoutubeDL object
        new Thread(youtubeDL).start(); // Runs the downloader in a new thread
    }
}
