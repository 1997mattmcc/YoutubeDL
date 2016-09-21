package youtubedl;

import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class YouTubeDL implements Runnable {

    private final Consumer<List<String>>[] terminationListeners;
    private final List<String> consoleLog = new ArrayList<>();
    private final Consumer<String>[] consoleLoggers;
    private final EnumMap<Options, String> options;
    private final EnumSet<Properties> properties;
    private boolean isDownloading = false;
    private final String downlaoder;
    private final String[] urls;

    public YouTubeDL(String downloader, EnumMap<Options, String> options, EnumSet<Properties> properties, String[] urls, Consumer<String>[] consoleLoggers, Consumer<List<String>>[] terminationListeners) {
        this.terminationListeners = terminationListeners;
        this.consoleLoggers = consoleLoggers;
        this.downlaoder = downloader;
        this.properties = properties;
        this.options = options;
        this.urls = urls;
    }

    public Consumer<List<String>>[] getTerminationListeners() {
        return terminationListeners;
    }

    public Consumer<String>[] getConsoleLoggers() {
        return consoleLoggers;
    }

    public EnumMap<Options, String> getOptions() {
        return options;
    }

    public EnumSet<Properties> getProperties() {
        return properties;
    }

    public List<String> getConsoleLog() {
        return consoleLog;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public String getDownloader() {
        return downlaoder;
    }

    public String[] getUrls() {
        return urls;
    }

    @Override
    public void run() {
        isDownloading = true;
        consoleLog.clear();
        try {
            String command = Objects.isNull(downlaoder) ? "youtube-dl" : downlaoder;
            String arguments = String.format("%s %s %s", getPropertiesAsString(properties), getOptionsAsString(options), getUrlsAsString(urls));
            Process process = Runtime.getRuntime().exec(String.join(" ", command, arguments));
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                    for (Consumer<String> logger : consoleLoggers) {
                        logger.accept(line);
                    }
                }
                for (Consumer<List<String>> logger : terminationListeners) {
                    logger.accept(consoleLog);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        isDownloading = false;
    }

    public static String getOptionsAsString(EnumMap<Options, String> options) {
        return options.entrySet().stream().map(entry -> {
            return String.join(" ", entry.getKey().getValue(), entry.getValue());
        }).collect(Collectors.joining(", "));
    }

    public static String getPropertiesAsString(EnumSet<Properties> properties) {
        return properties.stream().map(property -> {
            return property.getValue();
        }).collect(Collectors.joining(", "));
    }

    public static String getUrlsAsString(String[] urls) {
        return String.join(" ", urls);
    }
}
