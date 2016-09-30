package commandline;

import java.util.function.Consumer;
import java.util.function.Function;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;
import java.util.List;

public class Command implements Runnable {

    public static final Consumer<List<String>> DEFAULT_TERMINATION_LOGGER = consoleLog -> System.out.println(String.format("ConsoleLog = [ %s ] lines", consoleLog.size()));
    public static final Consumer<String> DEFAULT_CONSOLE_LOGGER = consoleLine -> System.out.println(String.format("ConsoleLine = [ %s ]", consoleLine));
    public static final Consumer<Command> DEFAULT_INITIATION_LOGGER = command -> System.out.println(String.format("Command = [ %s ]", command.command));
    public static final Function<String, String> DEFAULT_CONSOLE_FILTER = consoleLine -> consoleLine;

    public final Function<String, String> consoleFilter;
    public final Consumer<List<String>> termination;
    public final Consumer<Command> initiation;
    public final Consumer<String> console;
    public final String command;

    protected Command(CommandBuilder builder) {
        this.command = builder.getCommandString().replaceAll("\\s+", " ");
        this.consoleFilter = builder.consoleFilter;
        this.termination = builder.termination;
        this.initiation = builder.initiation;
        this.console = builder.console;
    }

    @Override
    public final void run() {
        try {
            List<String> consoleLog = new ArrayList<>();
            initiation.accept(this);
            Process process = Runtime.getRuntime().exec(command);
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                    if (!(line = consoleFilter.apply(line)).isEmpty()) {
                        console.accept(line);
                        consoleLog.add(line);
                    }
                }
            }
            termination.accept(consoleLog);
        } catch (IOException ex) {
            Logger.getLogger(commandline.executables.youtubedl.YoutubeDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
