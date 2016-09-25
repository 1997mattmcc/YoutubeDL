package youtubedl;

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
    HELP("--help");

    private final String property;

    private Properties(String property) {
        this.property = property;
    }

    public String getValue() {
        return property;
    }
}
