package musicPlayerSystem.MusicPlayerApplication.strategies;

import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class SequentialPlayStrategy implements IPlayStrategy {
    
    private Playlist currentPlaylist;
    private int currentIndex;

    public SequentialPlayStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex + 1 < currentPlaylist.getSize();
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No Playlist is Loaded or Playlist is empty");
        }

        currentIndex = (currentIndex + 1) % currentPlaylist.getSize();
        return currentPlaylist.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex - 1 >= 0;
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No Playlist is Loaded or Playlist is empty");
        }

        currentIndex = (currentIndex - 1 + currentPlaylist.getSize()) % currentPlaylist.getSize();
        return currentPlaylist.getSongs().get(currentIndex);
    }
}
