package musicPlayerSystem.MusicPlayerApplication.device;

import musicPlayerSystem.MusicPlayerApplication.external.HeadphonesAPI;
import musicPlayerSystem.MusicPlayerApplication.models.Song;

public class HeadphonesAdaptor implements IAudioOutputDevice {

    private HeadphonesAPI headphonesApi;

    public HeadphonesAdaptor(HeadphonesAPI headphonesApi) {
        this.headphonesApi = headphonesApi;
    }

    @Override
    public void playAudio(Song song) {
        // Code to connect to headphones and play the song
        String payload = song.getTitle() + " by " + song.getArtist();
        headphonesApi.playViaJack(payload);
    }
} 
