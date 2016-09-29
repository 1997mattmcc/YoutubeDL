package commandline.youtubedl;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import java.util.List;

public class YoutubeDLBuilder {

    protected EnumSet<Properties> properties = EnumSet.noneOf(Properties.class);
    protected EnumMap<Options, String> options = new EnumMap(Options.class);
    protected List<String> urls = new ArrayList<>();

    protected Consumer<List<String>> terminationLogger = null;
    protected Consumer<String> initiationLogger = null;
    protected Consumer<String> consoleLogger = null;

    public YoutubeDLBuilder setProperties(EnumSet<Properties> properties) {
        this.properties = properties;
        return this;
    }

    public YoutubeDLBuilder setOptions(EnumMap<Options, String> options) {
        this.options = options;
        return this;
    }

    public YoutubeDLBuilder setUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    public YoutubeDLBuilder setTerminationLogger(Consumer<List<String>> terminationLogger) {
        this.terminationLogger = terminationLogger;
        return this;
    }

    public YoutubeDLBuilder setInitiationLogger(Consumer<String> initiationLogger) {
        this.initiationLogger = initiationLogger;
        return this;
    }

    public YoutubeDLBuilder setConsoleLogger(Consumer<String> consoleLogger) {
        this.consoleLogger = consoleLogger;
        return this;
    }

    public YoutubeDLBuilder property(Properties property) {
        this.properties.add(property);
        return this;
    }

    public YoutubeDLBuilder option(Options option, String value) {
        this.options.put(option, value);
        return this;
    }

    public YoutubeDLBuilder url(String url) {
        this.urls.add(url);
        return this;
    }

    public YoutubeDLBuilder termination(Consumer<List<String>> terminationLogger) {
        if (Objects.isNull(this.terminationLogger)) {
            return this.setTerminationLogger(terminationLogger);
        } else {
            return this.setTerminationLogger(this.terminationLogger.andThen(terminationLogger));
        }
    }

    public YoutubeDLBuilder initiation(Consumer<String> initiationLogger) {
        if (Objects.isNull(this.initiationLogger)) {
            return this.setInitiationLogger(initiationLogger);
        } else {
            return this.setInitiationLogger(this.initiationLogger.andThen(initiationLogger));
        }
    }

    public YoutubeDLBuilder console(Consumer<String> consoleLogger) {
        if (Objects.isNull(this.consoleLogger)) {
            return this.setConsoleLogger(consoleLogger);
        } else {
            return this.setConsoleLogger(this.consoleLogger.andThen(consoleLogger));
        }
    }

    public final YoutubeDL build() {
        return new YoutubeDL(this);
    }
}
