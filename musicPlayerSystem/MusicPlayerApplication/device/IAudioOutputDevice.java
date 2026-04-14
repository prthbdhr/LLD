package musicPlayerSystem.MusicPlayerApplication.device;

import musicPlayerSystem.MusicPlayerApplication.models.Song;

public interface IAudioOutputDevice {
    void playAudio(Song song);
}
