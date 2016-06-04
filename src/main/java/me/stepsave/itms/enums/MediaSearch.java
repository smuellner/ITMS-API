package me.stepsave.itms.enums;

/**
 *
 *
 * @author smuellner
 */
public enum MediaSearch {
    MOVIE("movie"),
    PODCAST("podcast"),
    MUSIC("music"),
    MUSIC_VIDEO("musicVideo"),
    AUDIOBOOK("audiobook"),
    SHORT_FILM("shortFilm"),
    TV_SHOW("tvShow"),
    SOFTWARE("software"),
    EBOOK("ebook"),
    ALL("all");

    private final String text;

    /**
     * @param text
     */
    private MediaSearch(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
