package youtubedl;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class YoutubeDLBuilder {

    private final ArrayList<Consumer<List<String>>> terminationLoggers = new ArrayList<>();
    private final EnumSet<Properties> properties = EnumSet.noneOf(Properties.class);
    private final ArrayList<Consumer<String>> consoleLoggers = new ArrayList<>();
    private final EnumMap<Options, String> options = new EnumMap(Options.class);
    private final ArrayList<String> urls = new ArrayList<>();
    private String downlaoder = null;

    public YouTubeDL build() {
        String[] urlArray = urls.toArray(new String[urls.size()]);
        Consumer<String>[] consoleLoggersArray = consoleLoggers.toArray(new Consumer[consoleLoggers.size()]);
        Consumer<List<String>>[] terminationLoggersArray = terminationLoggers.toArray(new Consumer[terminationLoggers.size()]);
        YouTubeDL youtube = new YouTubeDL(downlaoder, options, properties, urlArray, consoleLoggersArray, terminationLoggersArray);
        return youtube;
    }

    public YoutubeDLBuilder withTerminationLogger(Consumer<List<String>> logger) {
        terminationLoggers.add(logger);
        return this;
    }

    public YoutubeDLBuilder withConsoleLogger(Consumer<String> logger) {
        consoleLoggers.add(logger);
        return this;
    }

    public YoutubeDLBuilder withDownloader(String downloader) {
        this.downlaoder = downloader;
        return this;
    }

    public YoutubeDLBuilder withOption(Options option, String value) {
        options.put(option, value);
        return this;
    }

    public YoutubeDLBuilder withProperty(Properties property) {
        properties.add(property);
        return this;
    }

    public YoutubeDLBuilder withURL(String url) {
        urls.add(url);
        return this;
    }
}
