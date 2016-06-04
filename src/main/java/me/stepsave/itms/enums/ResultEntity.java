package me.stepsave.itms.enums;

/**
 * 
 * The following entities are available for each media type:
 * 
 * movie	movieArtist, movie
 * podcast	podcastAuthor, podcast
 * music	musicArtist, musicTrack, album, musicVideo, mix, song
 * musicVideo	musicArtist, musicVideo
 * audiobook	audiobookAuthor, audiobook
 * shortFilm	shortFilmArtist, shortFilm, tvShow, tvEpisode, tvSeason
 * software	software, iPadSoftware, macSoftware
 * ebook        ebook
 * all          movie, album, allArtist, podcast, musicVideo, mix, audiobook, tvSeason, allTrack
 * 
 * @author smuellner
 */
public enum ResultEntity {
    MOVIE_AUTHOR("movieAuthor"),
    MOVIE("movie"),
    PODCAST_AUTHOR("podcastAuthor"),
    PODCAST("podcast"),
    MUSIC_ARTIST("musicArtist"),
    MUSIC_TRACK("musicTrack"),
    ALBUM("album"),
    MUSIC_VIDEO("musicVideo"),
    MIX("mix"),
    SONG("song");
    
    private final String text;

    /**
     * @param text
     */
    private ResultEntity(final String text) {
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
