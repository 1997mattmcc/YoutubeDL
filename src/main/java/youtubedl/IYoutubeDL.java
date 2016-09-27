package youtubedl;

import java.util.stream.Collectors;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.EnumMap;
import java.util.Objects;
import java.util.EnumSet;
import java.util.List;

public interface IYoutubeDL extends Runnable {

    public void logged(String consoleLine);

    public void terminated(List<String> consoleLog);

    public EnumMap<Options, String> getOptions();

    public EnumSet<Properties> getProperties();

    public Optional<String> getDownloader();

    public List<String> getUrls();

    @Override
    public default void run() {
        try {
            String command = this.getCommandString();
            List<String> consoleLog = new ArrayList<>();
            Process process = Runtime.getRuntime().exec(command);
            Logger.getLogger(IYoutubeDL.class.getName()).log(Level.CONFIG, String.format("Executing { %s }", command));
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream())) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                for (String line = bufferedReader.readLine(); Objects.nonNull(line); line = bufferedReader.readLine()) {
                    Logger.getLogger(IYoutubeDL.class.getName()).log(Level.FINEST, String.format("Executing { %s }", command));
                    consoleLog.add(line);
                    this.logged(line);
                }
                Logger.getLogger(IYoutubeDL.class.getName()).log(Level.CONFIG, "Execution Complete");
                this.terminated(consoleLog);
            }
        } catch (IOException ex) {
            Logger.getLogger(IYoutubeDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public default String getCommandString() {
        String options = this.getOptions().entrySet().stream().map(entry -> String.join(" ", entry.getKey().toString(), entry.getValue())).collect(Collectors.joining(", "));
        String properties = this.getProperties().stream().map(property -> property.toString()).collect(Collectors.joining(", "));
        String command = this.getDownloader().orElse("youtube-dl");
        String urls = String.join(" ", this.getUrls());
        return String.format("%s %s %s %s", command, options, properties, urls);
    }
}
