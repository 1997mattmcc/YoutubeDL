package commandline.executables.mp3gain;

import java.util.stream.Collectors;
import commandline.CommandBuilder;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import java.util.List;

public class MP3GainBuilder extends CommandBuilder {

    private EnumSet<MP3Gain.Properties> properties = EnumSet.noneOf(MP3Gain.Properties.class);
    private EnumMap<MP3Gain.Options, String> options = new EnumMap(MP3Gain.Options.class);
    private List<String> files = new ArrayList<>();

    public MP3GainBuilder setProperties(EnumSet<MP3Gain.Properties> properties) {
        return Objects.nonNull(this.properties = properties) ? this : null;
    }

    public MP3GainBuilder setOptions(EnumMap<MP3Gain.Options, String> options) {
        return Objects.nonNull(this.options = options) ? this : null;
    }

    public MP3GainBuilder setFiles(List<String> files) {
        return Objects.nonNull(this.files = files) ? this : null;
    }

    public MP3GainBuilder property(MP3Gain.Properties property) {
        return Objects.nonNull(this.properties.add(property)) ? this : null;
    }

    public MP3GainBuilder option(MP3Gain.Options option, String value) {
        return Objects.nonNull(this.options.put(option, value)) ? this : null;
    }

    public MP3GainBuilder file(String file) {
        return Objects.nonNull(this.files.add(file)) ? this : null;
    }

    @Override
    protected String getCommandString() {
        String optionsString = options.entrySet().stream().map(entry -> String.join(" ", entry.getKey().toString(), entry.getValue())).collect(Collectors.joining(" "));
        String propertiesString = properties.stream().map(property -> property.toString()).collect(Collectors.joining(" "));
        return String.format("%s %s %s %s", MP3Gain.getExecutable(), propertiesString, optionsString, String.join(" ", files));
    }
}
