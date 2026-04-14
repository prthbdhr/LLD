# Music Player System - Low Level Design

## Project Overview
A comprehensive Low-Level Design (LLD) implementation of a **Music Player Application** built in Java. This project demonstrates core object-oriented design principles including Factory Pattern, Strategy Pattern, Singleton Pattern, and Adapter Pattern.

## Features
- **Song Library Management**: Create and manage a library of songs with metadata (title, artist, file path)
- **Playlist Management**: Create playlists and add/remove songs
- **Multiple Playback Strategies**: Sequential, Random, and Custom Queue playback modes
- **Device Management**: Support for multiple audio output devices (Bluetooth, Wired Speakers, Headphones)
- **Playback Controls**: Play, Pause, Resume, and Navigate (Previous/Next) functionality
- **Singleton Application**: Thread-safe singleton pattern for the main application

## Project Structure
```
musicPlayerSystem/
├── MusicPlayerApplication/
│   ├── Main.java                          # Entry point
│   ├── MusicPlayerApplication.java        # Main application singleton
│   ├── device/
│   │   ├── IAudioOutputDevice.java        # Device interface
│   │   ├── BluetoothSpeakerAdaptor.java   # Bluetooth adapter
│   │   ├── WiredSpeakerAdaptor.java       # Wired speaker adapter
│   │   ├── HeadphonesAdaptor.java         # Headphones adapter
│   │   ├── BluetoothSpeakerApi.java       # External Bluetooth API
│   │   ├── WiredSpeakerAPI.java           # External Wired API
│   │   └── HeadphonesAPI.java             # External Headphones API
│   ├── enums/
│   │   ├── DeviceType.java                # Device type enumeration
│   │   └── PlayStrategyType.java          # Play strategy enumeration
│   ├── factories/
│   │   └── DeviceFactory.java             # Factory for device creation
│   ├── models/
│   │   ├── Song.java                      # Song entity
│   │   └── Playlist.java                  # Playlist entity
│   ├── playback/
│   │   ├── PlayStrategy.java              # Strategy interface
│   │   ├── SequentialPlayStrategy.java    # Sequential strategy
│   │   ├── RandomPlayStrategy.java        # Random shuffle strategy
│   │   └── CustomQueuePlayStrategy.java   # Custom queue strategy
│   └── external/
│       ├── BluetoothSpeakerApi.java
│       ├── WiredSpeakerAPI.java
│       └── HeadphonesAPI.java
```

## Design Patterns Used

### 1. **Singleton Pattern**
- **Class**: `MusicPlayerApplication`
- **Purpose**: Ensures only one instance of the music player application exists throughout the application lifecycle
- **Benefit**: Central control point for all music player operations

### 2. **Factory Pattern**
- **Class**: `DeviceFactory`
- **Purpose**: Encapsulates the creation of audio output devices
- **Benefit**: Decouples device creation from client code; easy to add new device types

### 3. **Adapter Pattern**
- **Classes**: `BluetoothSpeakerAdaptor`, `WiredSpeakerAdaptor`, `HeadphonesAdaptor`
- **Purpose**: Adapts external third-party APIs to a common interface (`IAudioOutputDevice`)
- **Benefit**: Allows integration of different external libraries without modifying core code

### 4. **Strategy Pattern**
- **Interface**: `PlayStrategy`
- **Implementations**: `SequentialPlayStrategy`, `RandomPlayStrategy`, `CustomQueuePlayStrategy`
- **Purpose**: Encapsulates different playback algorithms; allows runtime selection
- **Benefit**: Easy to add new playback strategies without modifying existing code

## Class Responsibilities

### Core Classes

| Class | Responsibility |
|-------|-----------------|
| `MusicPlayerApplication` | Central orchestrator; manages library, playlists, playback, and device connections |
| `Song` | Represents a music track with title, artist, and file path |
| `Playlist` | Container for songs; maintains list of songs in a playlist |
| `DeviceFactory` | Creates appropriate audio device adapters based on device type |

### Device Layer

| Class | Responsibility |
|-------|-----------------|
| `IAudioOutputDevice` | Interface defining contract for all audio output devices |
| `BluetoothSpeakerAdaptor` | Adapts Bluetooth speaker API to standard device interface |
| `WiredSpeakerAdaptor` | Adapts wired speaker API to standard device interface |
| `HeadphonesAdaptor` | Adapts headphones API to standard device interface |

### Playback Strategy

| Class | Responsibility |
|-------|-----------------|
| `PlayStrategy` | Interface defining contract for playback strategies |
| `SequentialPlayStrategy` | Plays songs in order from the playlist |
| `RandomPlayStrategy` | Plays songs in random order |
| `CustomQueuePlayStrategy` | Plays songs in a custom user-defined order |

## Key Interactions

### Playback Flow
1. User selects a playback strategy via `selectPlayStrategy()`
2. User loads a playlist via `loadPlaylist()`
3. Application delegates playback to the selected `PlayStrategy` implementation
4. Strategy determines song order and returns next/previous track
5. Audio is output through the connected device (`IAudioOutputDevice`)

### Device Connection Flow
1. User calls `connectToAudioDevice(DeviceType)`
2. `DeviceFactory.createDevice()` creates appropriate adaptor
3. Adaptor wraps external API and implements `IAudioOutputDevice`
4. All subsequent playback uses the connected device

## Usage Example

```java
// Initialize application
MusicPlayerApplication app = MusicPlayerApplication.getInstance();

// Build library
app.createSongInLibrary("Song Title", "Artist Name", "/path/to/file.mp3");

// Create and manage playlists
app.createPlaylist("My Playlist");
app.addSongToPlaylist("My Playlist", "Song Title");

// Connect to device
app.connectToAudioDevice(DeviceType.BLUETOOTH);

// Select playback strategy
app.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);

// Load and play
app.loadPlaylist("My Playlist");
app.playAllTracksInPlaylist();

// Playback controls
app.pauseCurrentSong("Song Title");
app.playPreviousTrackInPlaylist();
```

## Extension Points

### Adding a New Playback Strategy
1. Create a new class implementing `PlayStrategy`
2. Add corresponding entry to `PlayStrategyType` enum
3. Strategy is automatically available in the application

### Adding a New Audio Device
1. Create external API wrapper class
2. Create adaptor implementing `IAudioOutputDevice`
3. Add `DeviceType` enum entry
4. Update `DeviceFactory.createDevice()` with new case

## Design Principles Applied

- **SOLID Principles**: Single Responsibility, Open/Closed, Dependency Inversion
- **Separation of Concerns**: Distinct layers for business logic, devices, and strategies
- **Loose Coupling**: Dependencies injected through constructors and factory methods
- **High Cohesion**: Related functionality grouped within appropriate classes

## Error Handling

- Try-catch blocks in `Main.java` demonstrate exception handling
- Factory throws `IllegalArgumentException` for unsupported device types
- Application methods can throw exceptions for invalid operations

## Future Enhancements

- Shuffle with seed for reproducible randomization
- Repeat modes (Repeat All, Repeat One)
- Volume control
- Equalizer support
- Save/load playlist persistence
- Search and filter songs by artist, genre, etc.
- Concurrent playback on multiple devices

---

**Author**: System Design Study  
**Date**: April 14, 2026  
**Repository**: prthbdhr/lld