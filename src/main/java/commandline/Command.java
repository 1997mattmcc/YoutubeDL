package commandline;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.List;

public class Command {

    public static final Consumer<List<String>> DEFAULT_TERMINATION_LOGGER = consoleLog -> System.out.println(String.format("ConsoleLog = [ %s ] lines", consoleLog.size()));
    public static final Consumer<String> DEFAULT_CONSOLE_LOGGER = consoleLine -> System.out.println(String.format("ConsoleLine = [ %s ]", consoleLine));
    public static final Consumer<String> DEFAULT_INITIATION_LOGGER = command -> System.out.println(String.format("Command = [ %s ]", command));
    public static final Function<String, String> DEFAULT_CONSOLE_FILTER = consoleLine -> consoleLine;

    public final Function<String, String> consoleFilter;
    public final Consumer<List<String>> termination;
    public final Consumer<String> initiation;
    public final Consumer<String> console;
    public final String command;

    protected Command(CommandBuilder builder) {
        this.command = builder.getCommandString().replaceAll("\\s+", " ");
        this.consoleFilter = builder.consoleFilter;
        this.termination = builder.termination;
        this.initiation = builder.initiation;
        this.console = builder.console;
    }
}
