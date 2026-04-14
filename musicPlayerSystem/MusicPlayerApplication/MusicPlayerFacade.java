package musicPlayerSystem.MusicPlayerApplication;

import musicPlayerSystem.MusicPlayerApplication.core.AudioEngine;
import musicPlayerSystem.MusicPlayerApplication.device.IAudioOutputDevice;
import musicPlayerSystem.MusicPlayerApplication.enums.DeviceType;
import musicPlayerSystem.MusicPlayerApplication.enums.PlayStrategyType;
import musicPlayerSystem.MusicPlayerApplication.managers.DeviceManager;
import musicPlayerSystem.MusicPlayerApplication.managers.PlaylistManager;
import musicPlayerSystem.MusicPlayerApplication.managers.StrategyManager;
import musicPlayerSystem.MusicPlayerApplication.models.Playlist;
import musicPlayerSystem.MusicPlayerApplication.models.Song;
import musicPlayerSystem.MusicPlayerApplication.strategies.IPlayStrategy;

public class MusicPlayerFacade {

    private static MusicPlayerFacade instance = null;
    private AudioEngine audioEngine;
    private Playlist loadedPlaylist;
    private IPlayStrategy playStrategy;

    private MusicPlayerFacade() {
        loadedPlaylist = null;
        playStrategy = null;
        this.audioEngine = new AudioEngine();
    }

    public static MusicPlayerFacade getInstance() {

        if (instance == null) {
            synchronized (MusicPlayerFacade.class) {
                if (instance == null) {
                    instance = new MusicPlayerFacade();
                }
            }
        }

        return instance;
    }

    public void connectDevice(DeviceType deviceType) {
        DeviceManager.getInstance().connect(deviceType);
    }

    public void setPlayStrategy(PlayStrategyType strategyType) {
        playStrategy = StrategyManager.getInstance().getCurrentStrategy(strategyType);
    }

    public void loadPlaylist(String name) {
        loadedPlaylist = PlaylistManager.getInstance().getPlaylistByName(name);

        if (loadedPlaylist != null) {
            playStrategy.setPlaylist(loadedPlaylist);
        } else {
            throw new RuntimeException("strategy not fond or cannot be set");
        }
    }

    public void play(Song song) {
        if (!DeviceManager.getInstance().hasConnectedDevice()) {
            throw new RuntimeException("No audio output device connected"); 
        }

        IAudioOutputDevice device = DeviceManager.getInstance().getConnectedOutputDevice();

        audioEngine.play(device, song);
    }

    public void pause(Song song) {

        if (!audioEngine.getCurrentSongTitle().equals(song.getTitle())) {
            throw new RuntimeException("Cannot pause a song that is not currently playing");
        } 

        audioEngine.pause();
    }

    public void playAllTracks() {

        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }

        while (playStrategy.hasNext()) {

            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getConnectedOutputDevice();
            audioEngine.play(device, nextSong);
        }

        System.out.println("Finished playing all tracks in the playlist: " + loadedPlaylist.getName());
    }

    public void playNextTrack() {

        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded...");
        }

        if (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getConnectedOutputDevice();
            audioEngine.play(device, nextSong);
        } else {
            System.out.println("No more tracks to play in the playlist: " + loadedPlaylist.getName());
        }
    }

    public void playPreviousTrack() {

        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded...");
        }

        if (playStrategy.hasPrevious()) {
            Song previousSong = playStrategy.previous();
            IAudioOutputDevice device = DeviceManager.getInstance().getConnectedOutputDevice();
            audioEngine.play(device, previousSong);
        } else {
            System.out.println("No previous tracks to play in the playlist: " + loadedPlaylist.getName());
        }
    }

    public void enQueueNext(Song song) {
        playStrategy.addToNext(song);
    }
}
