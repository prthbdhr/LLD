package musicPlayerSystem.MusicPlayerApplication.factories;

import musicPlayerSystem.MusicPlayerApplication.device.BluetoothSpeakerAdaptor;
import musicPlayerSystem.MusicPlayerApplication.device.HeadphonesAdaptor;
import musicPlayerSystem.MusicPlayerApplication.device.IAudioOutputDevice;
import musicPlayerSystem.MusicPlayerApplication.device.WiredSpeakerAdaptor;
import musicPlayerSystem.MusicPlayerApplication.enums.DeviceType;
import musicPlayerSystem.MusicPlayerApplication.external.BluetoothSpeakerApi;
import musicPlayerSystem.MusicPlayerApplication.external.HeadphonesAPI;
import musicPlayerSystem.MusicPlayerApplication.external.WiredSpeakerAPI;

public class DeviceFactory {
    
    public static IAudioOutputDevice createDevice(DeviceType type) {

        switch (type) {

            case BLUETOOTH:
                return new BluetoothSpeakerAdaptor(new BluetoothSpeakerApi());
            
            case WIRED:
                return new WiredSpeakerAdaptor(new WiredSpeakerAPI());
            
            case HEADPHONES:
                return new HeadphonesAdaptor(new HeadphonesAPI());

            default:
                return new HeadphonesAdaptor(new HeadphonesAPI());
        }
    }
}
