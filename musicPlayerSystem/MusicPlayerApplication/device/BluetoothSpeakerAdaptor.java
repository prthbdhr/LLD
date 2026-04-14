package musicPlayerSystem.MusicPlayerApplication.device;

import musicPlayerSystem.MusicPlayerApplication.models.Song;
import musicPlayerSystem.MusicPlayerApplication.external.BluetoothSpeakerApi;


public class BluetoothSpeakerAdaptor implements IAudioOutputDevice {

    private BluetoothSpeakerApi bluetoothSpeakerApi;

    public BluetoothSpeakerAdaptor(BluetoothSpeakerApi bluetoothSpeakerApi) {
        this.bluetoothSpeakerApi = bluetoothSpeakerApi;
    }
    
    @Override
    public void playAudio(Song song) {
        // Code to connect to Bluetooth speaker and play the song
        String payload = song.getTitle() + " by " + song.getArtist();
        bluetoothSpeakerApi.playViaBluetooth(payload);
    }

}