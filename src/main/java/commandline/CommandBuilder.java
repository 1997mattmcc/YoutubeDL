package commandline;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Objects;
import java.util.List;

public abstract class CommandBuilder {

    protected Consumer<List<String>> termination = Command.DEFAULT_TERMINATION_LOGGER;
    protected Function<String, String> consoleFilter = Command.DEFAULT_CONSOLE_FILTER;
    protected Consumer<Command> initiation = Command.DEFAULT_INITIATION_LOGGER;
    protected Consumer<String> console = Command.DEFAULT_CONSOLE_LOGGER;

    protected abstract String getCommandString();

    public CommandBuilder setTermination(Consumer<List<String>> termination) {
        return Objects.nonNull(this.termination = termination) ? this : null;
    }

    public CommandBuilder setConsoleFilter(Function<String, String> consoleFilter) {
        return Objects.nonNull(this.consoleFilter = consoleFilter) ? this : null;
    }

    public CommandBuilder setInitiation(Consumer<Command> initiation) {
        return Objects.nonNull(this.initiation = initiation) ? this : null;
    }

    public CommandBuilder setConsole(Consumer<String> console) {
        return Objects.nonNull(this.console = console) ? this : null;
    }

    public Command build() {
        return new Command(this);
    }
}
