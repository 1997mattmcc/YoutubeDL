package commandline;

import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class CommandLine {

    private final List<Command> commands = Collections.synchronizedList(new ArrayList<>());
    private final Thread thread = new Thread(() -> this.run());

    public void queueCommand(Command command) {
        commands.add(command);
        if (!thread.isAlive()) {
            thread.run();
        }
    }

    private void run() {
        try {
            while (!commands.isEmpty()) {
                Command command = commands.remove(0);
                List<String> consoleLog = new ArrayList<>();
                command.initiation.accept(command.command);
                Process process = Runtime.getRuntime().exec(command.command);
                try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                        if (!(line = command.consoleFilter.apply(line)).isEmpty()) {
                            command.console.accept(line);
                            consoleLog.add(line);
                        }
                    }
                }
                command.termination.accept(consoleLog);
            }
        } catch (IOException ex) {
            Logger.getLogger(commandline.executables.youtubedl.YoutubeDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
