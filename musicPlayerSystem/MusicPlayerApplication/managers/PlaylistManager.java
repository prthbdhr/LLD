package musicPlayerSystem.MusicPlayerApplication.managers;

import java.util.HashMap;
import java.util.Map;

import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class PlaylistManager {
    
    private static PlaylistManager instance = null;
    private Map<String, Playlist> playlists;

    private PlaylistManager() {
        playlists = new HashMap<>();
    }

    public static PlaylistManager getInstance() {
        if (instance == null) {
            synchronized (PlaylistManager.class) {
                if (instance == null) {
                    instance = new PlaylistManager();
                }
            }
        }
        return instance;
    }

    public void createPlaylist(String playlistName) {

        if (playlistName == null || playlistName.trim().isEmpty()) {
            throw new IllegalArgumentException("Playlist name cannot be null or empty");
        }
        if (playlists.containsKey(playlistName)) {
            throw new IllegalArgumentException("Playlist with the same name already exists");
        }
        Playlist playlist = new Playlist(playlistName);
        playlists.put(playlistName, playlist);
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        
        if (playlistName == null || playlistName.trim().isEmpty()) {
            throw new IllegalArgumentException("Playlist name cannot be null or empty");
        }
        
        if (!playlists.containsKey(playlistName)) {
            throw new IllegalArgumentException("Playlist does not exist");
        }

        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }

        Playlist playlist = playlists.get(playlistName);

        if (playlist == null) {
            throw new IllegalArgumentException("Playlist does not exist");
        }

        if (playlist.getSongs().contains(song)) {
            throw new IllegalArgumentException("Song already exists in the playlist");
        }

        playlist.addSongToPlaylist(song);
    }

    public Playlist getPlaylistByName(String playlistName) {
        if (playlistName == null || playlistName.trim().isEmpty()) {
            throw new IllegalArgumentException("Playlist name cannot be null or empty");
        }

        if (!playlists.containsKey(playlistName)) {
            throw new IllegalArgumentException("Playlist does not exist");
        }
        
        return playlists.get(playlistName);
    }
}