package commandline.mp3gain;

public enum Properties {

    AUTO_APPLY_TRACK_GAIN_ALBUM("/va"),
    OUTPUT_DATABASE_FRIENDLY("/o"),
    FORCE_RECALCULATE_TAG("/s r"),
    AUTO_APPLY_TRACK_GAIN("/r"),
    AUTO_LOWER_NOT_CLIP("/k"),
    SKIP_ALBUM_ANALYSIS("/e"),
    WRITE_TO_TEMP_FIRST("/t"),
    DELETE_STORED_TAG("/s d"),
    PRESERVE_TIMESTAMP("/p"),
    CHECK_STORED_TAG("/s c"),
    UNDO_CHANGES_MODE("/u"),
    FIND_MAX_AMP_OMLY("/x"),
    SKIP_STORED_TAG("/s s"),
    WRAP_GAIN_CHANGE("/w"),
    IGNORE_CLIPPING("/c"),
    USE_ID3V2_TAG("/s i"),
    USE_APE_TAG("/s a"),
    ASSUME_MPEG_2("/f"),
    VERSION("/v"),
    ASSUME("/f"),
    QUIET("/q"),
    HELP("/h");

    private final String property;

    private Properties(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return property;
    }
}
