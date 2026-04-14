package musicPlayerSystem.MusicPlayerApplication;

import musicPlayerSystem.MusicPlayerApplication.enums.DeviceType;
import musicPlayerSystem.MusicPlayerApplication.enums.PlayStrategyType;
import musicPlayerSystem.MusicPlayerApplication.managers.PlaylistManager;
import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class MusicPlayerApplication {

    private static MusicPlayerApplication instance = null;
    private java.util.List<Song> songLibrary;

    private  MusicPlayerApplication() {
        songLibrary = new java.util.ArrayList<>();
    }

    public static MusicPlayerApplication getInstance() {
        if (instance == null) {
            synchronized (MusicPlayerApplication.class) {
                if (instance == null) {
                    instance = new MusicPlayerApplication();
                }
            }
        }
        return instance;
    }

    public void createSongInLibrary(String title, String artist, String filePath) {
        Song song = new Song(title, artist, filePath);
        songLibrary.add(song);
    }

    public Song getSongByTitle(String title) {
        for (Song song : songLibrary) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null; // Song not found
    }

    public void createPlaylist(String playlistName) {
        PlaylistManager.getInstance().createPlaylist(playlistName);
    }

    public void addSongToPlaylist(String playlistName, String songTitle) {
        Song song = getSongByTitle(songTitle);
        if (song == null) {
            throw new IllegalArgumentException("Song with the given title does not exist in the library");
        }
        PlaylistManager.getInstance().addSongToPlaylist(playlistName, song);
    }

    public void connectToAudioDevice(DeviceType deviceType) {
        MusicPlayerFacade.getInstance().connectDevice(deviceType);
    }

    public void selectPlayStrategy(PlayStrategyType type) {
        MusicPlayerFacade.getInstance().setPlayStrategy(type);
    }

    public void loadPlaylist(String playlistName) {
        Playlist playlist = PlaylistManager.getInstance().getPlaylistByName(playlistName);
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist with the given name does not exist");
        }

        MusicPlayerFacade.getInstance().loadPlaylist(playlistName);
    }

    public void playSingleSong(String songName) {
        Song song = getSongByTitle(songName);

        if (song == null) {
            throw new IllegalArgumentException("Song with the given title does not exist in the library...");
        }

        MusicPlayerFacade.getInstance().play(song);
    }

    public void pauseCurrentSong(String songName) {
        Song song = getSongByTitle(songName);

        if (song == null) {
            throw new IllegalArgumentException("Song with the given title does not exist in the library...");
        }

        MusicPlayerFacade.getInstance().pause(song);
    }

    public void playAllTracksInPlaylist() {
        MusicPlayerFacade.getInstance().playAllTracks();
    }

    public void playPreviousTrackInPlaylist() {
        MusicPlayerFacade.getInstance().playPreviousTrack();
    }

    public void QueuesNextSong(String songName){
        Song song = getSongByTitle(songName);

        if (song == null) {
            throw new IllegalArgumentException("Song with the given title does not exist in the library...");
        }

        MusicPlayerFacade.getInstance().enQueueNext(song);
    }
}