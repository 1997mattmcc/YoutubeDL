package commandline.executables.mp3gain;

public class MP3Gain {

    private static String executable = "mp3gain";

    public static String getExecutable() {
        return MP3Gain.executable;
    }

    public static void setExecutable(String executable) {
        MP3Gain.executable = executable;
    }

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
}
