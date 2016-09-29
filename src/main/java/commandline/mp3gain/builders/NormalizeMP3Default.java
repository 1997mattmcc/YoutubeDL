package commandline.mp3gain.builders;

import commandline.mp3gain.MP3GainBuilder;
import commandline.mp3gain.Properties;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalizeMP3Default extends MP3GainBuilder {

    private final List<String> queued = new ArrayList<>();
    private final Consumer<String> normalizedListener;
    private boolean normalizing = false;

    public NormalizeMP3Default(Consumer<String> normalizedListener) {
        this.normalizedListener = normalizedListener;

        super.termination(consoleLog -> this.termination(consoleLog));
        super.property(Properties.AUTO_APPLY_TRACK_GAIN);
        super.property(Properties.IGNORE_CLIPPING);
    }

    public void normalize(List<String> files) {
        System.out.println(String.join(" ", files));
        queued.addAll(files);
        if (!normalizing) {
            normalizing = true;
            new Thread(super.setFiles(Arrays.asList(queued.get(0))).build()).start();
        }
    }

    private void termination(List<String> consoleLog) {
        consoleLog.stream().map((consoleLine) -> {
            return consoleLine;
        }).filter((consoleLine) -> {
            return queued.remove(consoleLine);
        }).forEach((consoleLine) -> {
            normalizedListener.accept(consoleLine);
        });
        normalizing = false;
        if (!queued.isEmpty()) {
            this.normalize(new ArrayList<>());
        }
    }
}
