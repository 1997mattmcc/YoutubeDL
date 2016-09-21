package youtubedl;

public enum Options {

    DOWNLOAD_ARCHIVE("--download-archive"),
    PLAYLIST_ITEMS("--playlist-items"),
    AUDIO_FORMAT("--audio-format"),
    OUTPUT("--output"),
    FORMAT("--format");

    private final String argument;

    private Options(String argument) {
        this.argument = argument;
    }

    public final String getValue() {
        return argument;
    }
}
