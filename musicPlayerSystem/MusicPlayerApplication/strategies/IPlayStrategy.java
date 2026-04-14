package musicPlayerSystem.MusicPlayerApplication.strategies;

import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public interface IPlayStrategy {
    void setPlaylist(Playlist playlist);
    boolean hasNext();
    Song next();
    boolean hasPrevious();
    Song previous();
    default void addToNext(Song song){};
}
