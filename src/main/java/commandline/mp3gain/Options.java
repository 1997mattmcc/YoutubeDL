package commandline.mp3gain;

public enum Options {

    GAIN_0("/l 0"),
    GAIN_1("/l 1"),
    MP3_GAIN("/m"),
    DB_GAIN("/d"),
    GAIN("/g");

    private final String option;

    private Options(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }
}
