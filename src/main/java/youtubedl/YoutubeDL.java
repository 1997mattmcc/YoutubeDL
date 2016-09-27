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

public class YoutubeDL implements Runnable {

    private final Consumer<List<String>> terminationLogger;
    private final List<String> consoleLog = new ArrayList<>();
    private final EnumMap<Options, String> options;
    private final Consumer<String> consoleLogger;
    private final EnumSet<Properties> properties;
    private boolean isDownloading = false;
    private final String downlaoder;
    private final List<String> urls;

    public YoutubeDL(YoutubeDLBuilder builder) {
        this.terminationLogger = builder.terminationLogger;
        this.consoleLogger = builder.consoleLogger;
        this.downlaoder = builder.downloader;
        this.properties = builder.properties;
        this.options = builder.options;
        this.urls = builder.urls;
    }

    public Consumer<List<String>> getTerminationLogger() {
        return terminationLogger;
    }

    public EnumMap<Options, String> getOptions() {
        return options;
    }

    public Consumer<String> getConsoleLogger() {
        return consoleLogger;
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

    public List<String> getUrls() {
        return urls;
    }

    @Override
    public void run() {
        isDownloading = true;
        consoleLog.clear();
        try {
            String command = Objects.isNull(downlaoder) ? "youtube-dl" : downlaoder;
            String arguments = String.format("%s %s %s", getPropertiesAsString(properties), getOptionsAsString(options), String.join(" ", urls));
            Process process = Runtime.getRuntime().exec(String.join(" ", command, arguments));
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                    consoleLog.add(line);
                    if (Objects.nonNull(consoleLogger)) {
                        consoleLogger.accept(line);
                    }
                }
                if (Objects.nonNull(terminationLogger)) {
                    terminationLogger.accept(consoleLog);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        isDownloading = false;
    }

    public static String getOptionsAsString(EnumMap<Options, String> options) {
        return options.entrySet().stream().map(entry -> {
            return String.join(" ", entry.getKey().toString(), entry.getValue());
        }).collect(Collectors.joining(", "));
    }

    public static String getPropertiesAsString(EnumSet<Properties> properties) {
        return properties.stream().map(property -> {
            return property.toString();
        }).collect(Collectors.joining(", "));
    }

    public static class YoutubeDLBuilder {

        private final EnumSet<Properties> properties = EnumSet.noneOf(Properties.class);
        private final EnumMap<Options, String> options = new EnumMap(Options.class);
        private final ArrayList<String> urls = new ArrayList<>();
        private Consumer<List<String>> terminationLogger = null;
        private Consumer<String> consoleLogger = null;
        private String downloader = null;

        public YoutubeDLBuilder withTerminationLogger(Consumer<List<String>> terminationLogger) {
            this.terminationLogger = terminationLogger;
            return this;
        }

        public YoutubeDLBuilder consoleLogger(Consumer<String> consoleLogger) {
            this.consoleLogger = consoleLogger;
            return this;
        }

        public YoutubeDLBuilder downloader(String downloader) {
            this.downloader = downloader;
            return this;
        }

        public YoutubeDLBuilder addOption(Options option, String value) {
            options.put(option, value);
            return this;
        }

        public YoutubeDLBuilder addProperty(Properties property) {
            properties.add(property);
            return this;
        }

        public YoutubeDLBuilder withURL(String url) {
            urls.add(url);
            return this;
        }

        public YoutubeDL build() {
            return new YoutubeDL(this);
        }
    }
}
