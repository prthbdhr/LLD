package musicPlayerSystem.MusicPlayerApplication.device;

import musicPlayerSystem.MusicPlayerApplication.external.WiredSpeakerAPI;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class WiredSpeakerAdaptor implements IAudioOutputDevice {
    private WiredSpeakerAPI wiredSpeakerApi;

    public WiredSpeakerAdaptor(WiredSpeakerAPI wiredSpeakerApi) {
        this.wiredSpeakerApi = wiredSpeakerApi;
    }

    @Override
    public void playAudio(Song song) {
        // Code to connect to wired speaker and play the song
        String payload = song.getTitle() + " by " + song.getArtist();
        wiredSpeakerApi.playViaWiredSpeaker(payload);
    }
} 




