package musicPlayerSystem.MusicPlayerApplication.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class RandomPlayStrategy implements IPlayStrategy {
    private Playlist currentPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> historyStack;
    private Random random;

    public RandomPlayStrategy() {
        currentPlaylist = null;
        random = new Random();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) return;

        remainingSongs = new ArrayList<>(currentPlaylist.getSongs());
        historyStack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return currentPlaylist != null && !remainingSongs.isEmpty();
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No Playlist is Loaded or Playlist is empty");
        }

        int index = random.nextInt(remainingSongs.size());
        Song selectedNextSong = remainingSongs.get(index);

        // Remove the selected song from the remaining songs list
        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(lastIndex, remainingSongs.get(lastIndex));
        remainingSongs.remove(lastIndex);

        historyStack.push(selectedNextSong);
        return selectedNextSong;
    }

    @Override
    public boolean hasPrevious() {
        return historyStack.size() > 0;
    }

    @Override
    public Song previous() {
        if (historyStack.isEmpty()) {
            throw new RuntimeException("No previous song available");
        }

        Song previousSong = historyStack.pop();
        return previousSong;
    }
}
