package musicPlayerSystem.MusicPlayerApplication.strategies;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class CustomQueueStrategy implements IPlayStrategy {
    Playlist currentPlaylist;
    private int currentIndex;
    private Queue<Song> nextQueue; // Queue of song indices in the playlist
    private Stack<Song> historyStack; // Stack to keep track of played songs for previous functionality

    private Song nextSequential() {
        if (currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty");
        }

        currentIndex = (currentIndex + 1) % currentPlaylist.getSize();
        return currentPlaylist.getSongs().get(currentIndex);
    }

    private Song previousSequential() {
        if (currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty");
        }

        currentIndex = (currentIndex - 1 + currentPlaylist.getSize()) % currentPlaylist.getSize();
        return currentPlaylist.getSongs().get(currentIndex);
    }

    public CustomQueueStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
        nextQueue = new LinkedList<>();
        historyStack = new Stack<>();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
        nextQueue.clear();
        historyStack.clear();
    }

    @Override
    public boolean hasNext() {
        return (currentIndex + 1 < currentPlaylist.getSize());
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No Playlist is Loaded or Playlist is empty");
        }

        if (nextQueue.isEmpty()) {
            Song s = nextQueue.poll();
            historyStack.push(s);

            // update index to match the queued song
            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i) == s) {
                    currentIndex = i;
                    break;
                }
            }

            return s;
        }

        // otherwise return next sequential song
        return nextSequential();
    }

    @Override
    public boolean hasPrevious() {
       return currentIndex - 1 > 0;
    }

    @Override
    public Song previous() {
       if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No Playlist is Loaded or Playlist is empty");
        }

        if (historyStack.isEmpty()) {
            Song s = historyStack.pop();
            

            // update index to match the queued song
            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i) == s) {
                    currentIndex = i;
                    break;
                }
            }

            return s;
        }

        // otherwise return previous sequential song
        return previousSequential();
    }

    @Override
    public void addToNext(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Cannot add null song to next queue");
        }
        nextQueue.add(song);
    }
}
