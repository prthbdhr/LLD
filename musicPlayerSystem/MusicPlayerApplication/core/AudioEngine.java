package musicPlayerSystem.MusicPlayerApplication.core;

import musicPlayerSystem.MusicPlayerApplication.device.IAudioOutputDevice;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class AudioEngine {
    private Song currentSong;
    private boolean isPaused;

    public void loadSong(Song song) {
        this.currentSong = null;
        this.isPaused = false;
    }

    public String getCurrentSongTitle() {
        if (currentSong != null) {
            return currentSong.getTitle();
        }
        return "No song loaded";
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void play(IAudioOutputDevice audioOutputDevice, Song song) {
        
        if ( song == null) {
            System.out.println("No song loaded to play.");
            return;
        }

        //resume if same song is being paused
        if (isPaused &&  currentSong == song) {
            isPaused = false;
            System.out.println("Resuming " + song.getTitle());
            audioOutputDevice.playAudio(song);
            return;
        }

        // If a different song is loaded, stop the current song and play the new one
        if (currentSong != null && currentSong != song) {
            System.out.println("Stopping " + currentSong.getTitle());
            // audioOutputDevice.stopAudio();          
        }

        // Load and play the new song
        this.currentSong = song;
        this.isPaused = false;
        System.out.println("Playing " + song.getTitle());
        audioOutputDevice.playAudio(song);
    }

    public void pause() {
        if (currentSong == null) {
            throw new IllegalStateException("No song is currently playing to pause.");
        }

        if (!isPaused) {
            isPaused = true;
            System.out.println("Pausing " + currentSong.getTitle());
            // Code to pause the song
        } else {
            throw new RuntimeException("Song is already paused.");
        }
    }
}
