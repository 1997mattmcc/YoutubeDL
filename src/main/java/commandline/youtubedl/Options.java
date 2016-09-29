package commandline.youtubedl;

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
