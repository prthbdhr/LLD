package musicPlayerSystem.MusicPlayerApplication.models;
import java.util.List;
import java.util.ArrayList;

public class Playlist {

    private String name;
    private List<Song> songList;

    public Playlist(String name) {
        this.name = name;
        this.songList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songList;
    }

    public int getSize() {
        return songList.size();
    }

    public void addSongToPlaylist(Song song) {

        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        songList.add(song);
    }
}