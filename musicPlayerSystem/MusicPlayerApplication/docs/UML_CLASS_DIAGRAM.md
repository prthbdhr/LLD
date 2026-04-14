# UML Class Diagram - Music Player System

## Complete UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         MUSIC PLAYER SYSTEM - UML                           │
└─────────────────────────────────────────────────────────────────────────────┘

                            ┌──────────────────────┐
                            │ MusicPlayerApplication│
                            │      (Singleton)     │
                            ├──────────────────────┤
                            │ - instance: static   │
                            │ - songLibrary: Map   │
                            │ - playlists: Map     │
                            │ - currentDevice      │
                            │ - playStrategy       │
                            │ - currentPlaylist    │
                            ├──────────────────────┤
                            │ + getInstance()      │
                            │ + createSong()       │
                            │ + createPlaylist()   │
                            │ + addSongToPlaylist()│
                            │ + connectToDevice()  │
                            │ + selectStrategy()   │
                            │ + playAllTracks()    │
                            │ + playSingleSong()   │
                            │ + pauseSong()        │
                            │ + playNext()         │
                            │ + playPrevious()     │
                            └──────────┬───────────┘
                                       │
                    ┌──────────────────┼──────────────────┐
                    │                  │                  │
                    ▼                  ▼                  ▼
         ┌────────────────────┐ ┌──────────────┐ ┌────────────────────┐
         │     Song           │ │   Playlist   │ │  DeviceFactory     │
         ├────────────────────┤ ├──────────────┤ ├────────────────────┤
         │ - title: String    │ │ - name       │ │ - name: String     │
         │ - artist: String   │ │ - songs: List│ │                    │
         │ - filePath: String │ │              │ ├────────────────────┤
         ├────────────────────┤ ├──────────────┤ │ + createDevice()   │
         │ + getTitle()       │ │ + addSong()  │ │   (DeviceType)     │
         │ + getArtist()      │ │ + removeSong()│ └────────────────────┘
         │ + getFilePath()    │ │ + getSongs() │
         │ + toString()       │ └──────────────┘
         └────────────────────┘


┌────────────────────────────┐
│  IAudioOutputDevice        │
│      <<interface>>         │
├────────────────────────────┤
│ + connect(): void          │
│ + playAudio(data): void    │
│ + disconnect(): void       │
└────────┬──────────┬────────┘
         │          │
    ┌────┴──┐  ┌────┴──────────┐  ┌──────────────────────┐
    │       │  │               │  │                      │
    ▼       ▼  ▼               ▼  ▼                      ▼
┌──────────┐ ┌──────────┐ ┌──────────┐
│Bluetooth │ │  Wired   │ │Headphones│
│Speaker   │ │  Speaker │ │  Adaptor │
│Adaptor   │ │Adaptor   │ │          │
├──────────┤ ├──────────┤ ├──────────┤
│ - api    │ │ - api    │ │ - api    │
├──────────┤ ├──────────┤ ├──────────┤
│ + uses   │ │ + uses   │ │ + uses   │
│Bluetooth │ │WiredSpeaker│ │Headphones│
│SpeakerApi│ │API       │ │API       │
└──────────┘ └──────────┘ └──────────┘
    │            │            │
    └────┬────────┴────────────┘
         │
         │ adapts to
         │
    ┌────▼──────────┐
    │  External     │
    │  APIs Layer   │
    ├───────────────┤
    │ BluetoothAPI  │
    │ WiredAPI      │
    │ HeadphonesAPI │
    └───────────────┘


┌────────────────────────────────┐
│    PlayStrategy                │
│     <<interface>>              │
├────────────────────────────────┤
│ + getNextTrack(): Song         │
│ + getPreviousTrack(): Song     │
│ + reset(): void                │
└────────┬──────────┬────────────┘
         │          │
    ┌────┴─┐  ┌─────┴─────┐  ┌───────────────────┐
    │      │  │           │  │                   │
    ▼      ▼  ▼           ▼  ▼                   ▼
┌──────────────┐  ┌───────────────┐  ┌──────────────────┐
│ Sequential   │  │   Random      │  │  CustomQueue     │
│ PlayStrategy │  │ PlayStrategy  │  │  PlayStrategy    │
├──────────────┤  ├───────────────┤  ├──────────────────┤
│ - index      │  │ - random      │  │ - customQueue    │
│ - playlist   │  │ - playlist    │  │ - currentIndex   │
├──────────────┤  ├───────────────┤  ├──────────────────┤
│ + getNext()  │  │ + getNext()   │  │ + getNext()      │
│ + getPrev()  │  │ + getPrev()   │  │ + getPrev()      │
│ + reset()    │  │ + reset()     │  │ + queueTrack()   │
└──────────────┘  └───────────────┘  │ + reset()        │
                                      └──────────────────┘


┌──────────────────────────────┐
│      Enumerations            │
├──────────────────────────────┤
│   DeviceType                 │
│  ├─ BLUETOOTH               │
│  ├─ WIRED                   │
│  └─ HEADPHONES              │
│                              │
│   PlayStrategyType           │
│  ├─ SEQUENTIAL              │
│  ├─ RANDOM                  │
│  └─ CUSTOM_QUEUE            │
└──────────────────────────────┘
```

## Detailed Class Relationships

### 1. **Composition Relationships**

```
MusicPlayerApplication ──── composition ───→ IAudioOutputDevice
                                             (currentDevice)

MusicPlayerApplication ──── composition ───→ PlayStrategy
                                             (playStrategy)

MusicPlayerApplication ──── composition ───→ Playlist (Map)
                                             (playlists)

MusicPlayerApplication ──── composition ───→ Song (Map)
                                             (songLibrary)

Playlist ──────────────────→ Song (List)
                            (songs)
```

### 2. **Inheritance/Implementation Relationships**

```
BluetoothSpeakerAdaptor ──implements──→ IAudioOutputDevice
WiredSpeakerAdaptor      ──implements──→ IAudioOutputDevice
HeadphonesAdaptor        ──implements──→ IAudioOutputDevice

SequentialPlayStrategy   ──implements──→ PlayStrategy
RandomPlayStrategy       ──implements──→ PlayStrategy
CustomQueuePlayStrategy  ──implements──→ PlayStrategy
```

### 3. **Factory Relationship**

```
DeviceFactory ──uses──→ DeviceType (enum)
                    ├─→ BluetoothSpeakerAdaptor
                    ├─→ WiredSpeakerAdaptor
                    └─→ HeadphonesAdaptor
```

### 4. **Dependency Relationships**

```
MusicPlayerApplication ──uses──→ DeviceFactory
                            (to create devices)

MusicPlayerApplication ──uses──→ PlayStrategyType (enum)
                            (to select strategy)

Adaptors ──wrap──→ External APIs
                  (BluetoothSpeakerApi, WiredSpeakerAPI, HeadphonesAPI)
```

---

## Class Diagram Legend

| Symbol | Meaning |
|--------|---------|
| `─◇` | Composition (strong ownership) |
| `─◇` | Aggregation (weak ownership) |
| `───▶` | Association |
| `─└─▶` | Inheritance |
| `···▶` | Realization (implements interface) |
| `<<interface>>` | Interface stereotype |
| `static` | Class method/variable |

---

## Key Design Patterns Visible in UML

### 1. **Singleton Pattern**
- `MusicPlayerApplication` has private constructor and static instance

### 2. **Factory Pattern**
- `DeviceFactory.createDevice()` returns `IAudioOutputDevice`
- Encapsulates object creation logic

### 3. **Strategy Pattern**
- `PlayStrategy` interface with multiple implementations
- Runtime selection via `selectPlayStrategy()`

### 4. **Adapter Pattern**
- `BluetoothSpeakerAdaptor`, `WiredSpeakerAdaptor`, `HeadphonesAdaptor`
- Adapt external APIs to common `IAudioOutputDevice` interface

---

## Method Call Flow Diagram

### Playing a Song

```
Main
  │
  └─→ MusicPlayerApplication.getInstance()
       │
       ├─→ createSongInLibrary()
       │    └─→ Add to songLibrary Map
       │
       ├─→ createPlaylist()
       │    └─→ Add to playlists Map
       │
       ├─→ addSongToPlaylist()
       │    └─→ Playlist.addSong()
       │
       ├─→ connectToAudioDevice(DeviceType)
       │    └─→ DeviceFactory.createDevice(DeviceType)
       │         └─→ Returns IAudioOutputDevice implementation
       │
       ├─→ selectPlayStrategy(PlayStrategyType)
       │    └─→ Instantiate appropriate PlayStrategy
       │
       ├─→ loadPlaylist(name)
       │    └─→ Set currentPlaylist
       │
       ├─→ playAllTracksInPlaylist()
       │    └─→ PlayStrategy.getNextTrack()
       │         └─→ IAudioOutputDevice.playAudio()
       │
       ├─→ pauseCurrentSong()
       │
       └─→ playPreviousTrackInPlaylist()
            └─→ PlayStrategy.getPreviousTrack()
                 └─→ IAudioOutputDevice.playAudio()
```

---

## Class Diagram Details

### MusicPlayerApplication
```
┌─────────────────────────────────────────────────────┐
│          MusicPlayerApplication                     │
├─────────────────────────────────────────────────────┤
│ ATTRIBUTES:                                         │
│  - instance: MusicPlayerApplication (static)       │
│  - songLibrary: Map<String, Song>                   │
│  - playlists: Map<String, Playlist>                 │
│  - currentDevice: IAudioOutputDevice                │
│  - playStrategy: PlayStrategy                       │
│  - currentPlaylist: Playlist                        │
│  - currentSong: Song                                │
├─────────────────────────────────────────────────────┤
│ METHODS:                                            │
│  + getInstance(): MusicPlayerApplication (static)  │
│  + createSongInLibrary(title, artist, path)        │
│  + createPlaylist(name)                             │
│  + addSongToPlaylist(playlistName, songTitle)      │
│  + removeSongFromPlaylist(playlistName, songTitle) │
│  + connectToAudioDevice(deviceType)                │
│  + selectPlayStrategy(strategyType)                │
│  + loadPlaylist(name)                              │
│  + playAllTracksInPlaylist()                        │
│  + playSingleSong(songTitle)                        │
│  + pauseCurrentSong(songTitle)                      │
│  + playNextTrackInPlaylist()                        │
│  + playPreviousTrackInPlaylist()                    │
│  + queueSong(songTitle)                             │
└─────────────────────────────────────────────────────┘
```

### IAudioOutputDevice
```
┌─────────────────────────────────────┐
│   <<interface>>                     │
│   IAudioOutputDevice                │
├─────────────────────────────────────┤
│ METHODS (Abstract):                 │
│  + connect(): void                  │
│  + playAudio(audioData: String)    │
│  + disconnect(): void               │
└─────────────────────────────────────┘
```

### PlayStrategy
```
┌─────────────────────────────────────┐
│   <<interface>>                     │
│   PlayStrategy                      │
├─────────────────────────────────────┤
│ METHODS (Abstract):                 │
│  + getNextTrack(): Song             │
│  + getPreviousTrack(): Song         │
│  + reset(): void                    │
│  + setPlaylist(playlist: Playlist)  │
└─────────────────────────────────────┘
```

---

## Sequence Diagram - Play A Song

```
User    Main    App         Factory    Strategy   Device
  │       │      │              │         │        │
  ├──────→│      │              │         │        │
  │       │ getInstance()       │         │        │
  │       ├─────→│              │         │        │
  │       │      ├─────────────→│         │        │
  │       │      │              │ return  │        │
  │       │      │←─────────────┤ device  │        │
  │       │      │              │         │        │
  │       │ selectStrategy()    │         │        │
  │       ├─────→│              │         ├───────→│
  │       │      │              │         │ init   │
  │       │      │              │         │        │
  │       │ playAllTracks()     │         │        │
  │       ├─────→│              │         │        │
  │       │      ├─────────────────────────→│      │
  │       │      │              │         │ getNext│
  │       │      │              │         │        │
  │       │      │              │         ├──────→ │
  │       │      │              │         │        │ playAudio()
  │       │      │              │         │        │
  │       │      │ update UI    │         │        │
  └← ← ← ┴← ← ← ┴← ← ← ← ← ← ← ┴← ← ← ← ┴← ← ← ┴
```

---

**Last Updated**: April 14, 2026  
**Version**: 1.0