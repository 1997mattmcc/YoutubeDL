package youtubedl;

public enum Properties {

    YOUTUBE_SKIP_DASH_MANIFEST("--youtube-skip-dash-manifest"),
    WRITE_INFO_JSON("--write-info-json"),
    EXTRACT_AUDIO("--extract-audio"),
    IGNORE_ERRORS("--ignore-errors"),
    SKIP_DOWNLOAD("--skip-download"),
    PRINT_JSON("--print-json"),
    EXTRACT_AUDIO("--extract-audio"),
    VERBOSE("--verbose");

    private final String property;
    private String value;

    private Properties(String property) {
        this.property = property;
    }

    public final String getValue() {
        return property;
    }
}
