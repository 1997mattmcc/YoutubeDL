package commandline.executables.youtubedl;

public final class YoutubeDL {

    private static String executable = "youtube-dl";

    public static String getExecutable() {
        return YoutubeDL.executable;
    }

    public static void setExecutable(String executable) {
        YoutubeDL.executable = executable;
    }

    public enum Properties {

        YOUTUBE_SKIP_DASH_MANIFEST("--youtube-skip-dash-manifest"),
        WRITE_INFO_JSON("--write-info-json"),
        ABORT_ON_ERRORS("--abort-on-error"),
        EXTRACT_AUDIO("--extract-audio"),
        FLAT_PLAYLIST("--flat-playlist"),
        IGNORE_ERRORS("--ignore-errors"),
        SKIP_DOWNLOAD("--skip-download"),
        PRINT_JSON("--print-json"),
        VERBOSE("--verbose"),
        VERSION("--version"),
        GET_URL("--get-url"),
        GET_ID("--get-id"),
        QUIET("--quiet"),
        HELP("--help");

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

        DOWNLOAD_ARCHIVE("--download-archive"),
        PLAYLIST_ITEMS("--playlist-items"),
        AUDIO_QUALITY("--audio-quality"),
        AUDIO_FORMAT("--audio-format"),
        RATE_LIMIT("--limit-rate"),
        OUTPUT("--output"),
        FORMAT("--format"),
        EXEC("--exec");

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
