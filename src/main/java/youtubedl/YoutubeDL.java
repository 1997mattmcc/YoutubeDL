package youtubedl;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Optional;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class YoutubeDL implements IYoutubeDL {

    private final Consumer<List<String>> terminationLogger;
    private final EnumMap<Options, String> options;
    private final Consumer<String> consoleLogger;
    private final EnumSet<Properties> properties;
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

    @Override
    public void logged(String consoleLine) {
        consoleLogger.accept(consoleLine);
    }

    @Override
    public void terminated(List<String> consoleLog) {
        terminationLogger.accept(consoleLog);
    }

    @Override
    public EnumMap<Options, String> getOptions() {
        return options;
    }

    @Override
    public EnumSet<Properties> getProperties() {
        return properties;
    }

    @Override
    public Optional<String> getDownloader() {
        return Optional.of(downlaoder);
    }

    @Override
    public List<String> getUrls() {
        return urls;
    }

    public static class YoutubeDLBuilder {

        private final EnumSet<Properties> properties = EnumSet.noneOf(Properties.class);
        private final EnumMap<Options, String> options = new EnumMap(Options.class);
        private final ArrayList<String> urls = new ArrayList<>();
        private Consumer<List<String>> terminationLogger = null;
        private Consumer<String> consoleLogger = null;
        private String downloader = null;

        public YoutubeDLBuilder terminationLogger(Consumer<List<String>> terminationLogger) {
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

        public YoutubeDLBuilder addURL(String url) {
            urls.add(url);
            return this;
        }

        public YoutubeDL build() {
            return new YoutubeDL(this);
        }
    }
}
