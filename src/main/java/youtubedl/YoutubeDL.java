package youtubedl;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import java.util.List;

public final class YoutubeDL implements Runnable {

    public static final Consumer<List<String>> DEFAULT_TERMINATION_LOGGER = consoleLog -> System.out.println(String.format("ConsoleLog = [ %s ] lines", consoleLog.size()));
    public static final Consumer<String> DEFAULT_CONSOLE_LOGGER = consoleLine -> System.out.println(String.format("ConsoleLine =  [ %s ]", consoleLine));
    public static final Consumer<String> DEFAULT_INITIATION_LOGGER = command -> System.out.println(String.format("Command = [ %s ]", command));

    private static String executable = "youtube-dl";

    private final Consumer<List<String>> terminationLogger;
    private final Consumer<String> initiationLogger;
    private final EnumMap<Options, String> options;
    private final Consumer<String> consoleLogger;
    private final EnumSet<Properties> properties;
    private final List<String> urls;

    public YoutubeDL(YoutubeDLBuilder builder) {
        this.terminationLogger = builder.terminationLogger;
        this.initiationLogger = builder.initiationLogger;
        this.consoleLogger = builder.consoleLogger;
        this.properties = builder.properties;
        this.options = builder.options;
        this.urls = builder.urls;
    }

    private final void logged(String consoleLine) {
        if (Objects.nonNull(consoleLogger)) {
            consoleLogger.accept(consoleLine);
        }
    }

    private final void terminated(List<String> consoleLog) {
        if (Objects.nonNull(terminationLogger)) {
            terminationLogger.accept(consoleLog);
        }
    }

    private final void initiated(String command) {
        if (Objects.nonNull(initiationLogger)) {
            initiationLogger.accept(command);
        }
    }

    @Override
    public final void run() {
        String command = this.toString();
        try {
            this.initiated(command);
            List<String> consoleLog = new ArrayList<>();
            Process process = Runtime.getRuntime().exec(command);
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                    consoleLog.add(line);
                    this.logged(line);
                }
                this.terminated(consoleLog);
            }
        } catch (IOException ex) {
            Logger.getLogger(YoutubeDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public final String toString() {
        String optionsString = options.entrySet().stream().map(entry -> String.join(" ", entry.getKey().toString(), entry.getValue())).collect(Collectors.joining(" "));
        String propertiesString = properties.stream().map(property -> property.toString()).collect(Collectors.joining(" "));
        return String.format("%s %s %s %s", executable, propertiesString, optionsString, String.join(" ", urls));
    }

    public static final void setExecutable(String executable) {
        YoutubeDL.executable = executable;
    }
}
