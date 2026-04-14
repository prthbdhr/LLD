package musicPlayerSystem.MusicPlayerApplication.managers;

import musicPlayerSystem.MusicPlayerApplication.device.IAudioOutputDevice;
import musicPlayerSystem.MusicPlayerApplication.enums.DeviceType;
import musicPlayerSystem.MusicPlayerApplication.factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice ;

    public static DeviceManager getInstance() {

        if (instance == null) {
            synchronized (DeviceManager.class) {
                if (instance == null) {
                    instance = new DeviceManager();
                }
            }
        }

        return instance;
    }

    public void connect(DeviceType deviceType) {

        if (currentOutputDevice != null) {

            // in cpp we have to manually call destructor to free the memory, but in java we have garbage collector to do that for us, so we just need to set the reference to null and let the garbage collector do its job
            // in java garbege collector will automatically free the memory when there are no references to the object, so we just need to set the reference to null and let the garbage collector do its job
            currentOutputDevice = null; // dummy to avoid error, in real code we will have to call the destructor of the current output device to free the memory, but in java we don't have to do that because of garbage collector
        }

        currentOutputDevice = DeviceFactory.createDevice(deviceType);

        switch (deviceType) {
            case BLUETOOTH:
                System.out.println("Connected to Bluetooth Speaker...");
                break;
            
            case WIRED:
                System.out.println("Connected to Wired Speaker...");
                break;
            case HEADPHONES:
                System.out.println("Connected to Headphones...");
                break;
            default:
                System.out.println("Invalid device type.");
                break;
        }
    }

    public IAudioOutputDevice getConnectedOutputDevice() {
        if (!hasConnectedDevice()) {
            System.out.println("oops! No output device connected.");
            
        }

        return currentOutputDevice;
    }

    public boolean hasConnectedDevice() {
        return currentOutputDevice != null;
    }
}
