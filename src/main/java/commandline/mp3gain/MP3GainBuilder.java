package commandline.mp3gain;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import java.util.List;

public class MP3GainBuilder {

    protected EnumSet<Properties> properties = EnumSet.noneOf(Properties.class);
    protected EnumMap<Options, String> options = new EnumMap(Options.class);
    protected List<String> files = new ArrayList<>();

    protected Consumer<List<String>> terminationLogger = null;
    protected Consumer<String> initiationLogger = null;
    protected Consumer<String> consoleLogger = null;

    public MP3GainBuilder setProperties(EnumSet<Properties> properties) {
        this.properties = properties;
        return this;
    }

    public MP3GainBuilder setOptions(EnumMap<Options, String> options) {
        this.options = options;
        return this;
    }

    public MP3GainBuilder setFiles(List<String> files) {
        this.files = files;
        return this;
    }

    public MP3GainBuilder setTerminationLogger(Consumer<List<String>> terminationLogger) {
        this.terminationLogger = terminationLogger;
        return this;
    }

    public MP3GainBuilder setInitiationLogger(Consumer<String> initiationLogger) {
        this.initiationLogger = initiationLogger;
        return this;
    }

    public MP3GainBuilder setConsoleLogger(Consumer<String> consoleLogger) {
        this.consoleLogger = consoleLogger;
        return this;
    }

    public MP3GainBuilder property(Properties property) {
        this.properties.add(property);
        return this;
    }

    public MP3GainBuilder option(Options option, String value) {
        this.options.put(option, value);
        return this;
    }

    public MP3GainBuilder file(String file) {
        this.files.add(file);
        return this;
    }

    public MP3GainBuilder termination(Consumer<List<String>> terminationLogger) {
        if (Objects.isNull(this.terminationLogger)) {
            return this.setTerminationLogger(terminationLogger);
        } else {
            return this.setTerminationLogger(this.terminationLogger.andThen(terminationLogger));
        }
    }

    public MP3GainBuilder initiation(Consumer<String> initiationLogger) {
        if (Objects.isNull(this.initiationLogger)) {
            return this.setInitiationLogger(initiationLogger);
        } else {
            return this.setInitiationLogger(this.initiationLogger.andThen(initiationLogger));
        }
    }

    public MP3GainBuilder console(Consumer<String> consoleLogger) {
        if (Objects.isNull(this.consoleLogger)) {
            return this.setConsoleLogger(consoleLogger);
        } else {
            return this.setConsoleLogger(this.consoleLogger.andThen(consoleLogger));
        }
    }

    public final MP3Gain build() {
        return new MP3Gain(this);
    }
}
