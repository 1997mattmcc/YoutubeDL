package commandline.executables.youtubedl;

import java.util.stream.Collectors;
import commandline.CommandBuilder;
import java.util.ArrayList;
import java.util.Objects;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class YoutubeDLBuilder extends CommandBuilder {

    private EnumSet<YoutubeDL.Properties> properties = EnumSet.noneOf(YoutubeDL.Properties.class);
    private EnumMap<YoutubeDL.Options, String> options = new EnumMap(YoutubeDL.Options.class);
    private List<String> urls = new ArrayList<>();

    public YoutubeDLBuilder setProperties(EnumSet<YoutubeDL.Properties> properties) {
        return Objects.nonNull(this.properties = properties) ? this : null;
    }

    public YoutubeDLBuilder setOptions(EnumMap<YoutubeDL.Options, String> options) {
        return Objects.nonNull(this.options = options) ? this : null;
    }

    public YoutubeDLBuilder setUrls(List<String> urls) {
        return Objects.nonNull(this.urls = urls) ? this : null;
    }

    public YoutubeDLBuilder property(YoutubeDL.Properties property) {
        return Objects.nonNull(this.properties.add(property)) ? this : null;
    }

    public YoutubeDLBuilder option(YoutubeDL.Options option, String value) {
        return Objects.nonNull(this.options.put(option, value)) ? this : null;
    }

    public YoutubeDLBuilder url(String url) {
        return Objects.nonNull(this.urls.add(url)) ? this : null;
    }

    @Override
    protected String getCommandString() {
        String optionsString = options.entrySet().stream().map(entry -> String.join(" ", entry.getKey().toString(), entry.getValue())).collect(Collectors.joining(" "));
        String propertiesString = properties.stream().map(property -> property.toString()).collect(Collectors.joining(" "));

        //A youtube-dl limitation where a video id starts with "-", the parameter identifier...
        for (int i = 0; i < urls.size(); i++) {
            if (urls.get(i).startsWith("-")) {
                if (urls.get(i).length() == 11) {
                    urls.set(i, "youtube.com/watch?v=".concat(urls.get(i)));
                } else if (urls.get(i).length() == 34) {
                    urls.set(i, "youtube.com/playlist?list=".concat(urls.get(i)));
                }
            }
        }
        return String.format("%s %s %s %s", YoutubeDL.getExecutable(), propertiesString, optionsString, String.join(" ", urls));
    }
}
