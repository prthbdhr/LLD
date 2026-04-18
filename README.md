# Low Level Design (LLD) Projects

## Overview

This repository contains comprehensive implementations of various design patterns and architectural concepts used in building scalable, maintainable software systems. Each project demonstrates best practices in object-oriented design and architectural patterns.

---

## 📋 Table of Contents

1. [Projects Overview](#projects-overview-) - Summary of all projects
2. [Quick Start](#-quick-start) - Getting started guide
3. [Project Comparison](#-project-comparison-matrix) - Side-by-side comparison
4. [Learning Outcomes](#-learning-outcomes) - What you'll learn
5. [Design Patterns](#design-patterns-reference) - Pattern reference
6. [Project Structure](#project-structure) - File organization
7. [Building & Running](#building-and-running) - Compilation & execution
8. [Diagrams](#diagrams) - Visual representations
9. [Key Learnings](#key-learnings) - Important concepts
10. [Documentation](#-complete-documentation-index) - All resources
11. [Resources](#resources) - Books and references

---

## Projects Overview

### 1. Composite Pattern - File System Implementation

**Location**: `composite_pattern/`

A demonstration of the **Composite Design Pattern** through a hierarchical file system implementation.

#### What it demonstrates:
- Composite pattern for tree structures
- Treating individual objects and compositions uniformly
- Recursive operations on hierarchical data
- Elegant abstraction for part-whole hierarchies

#### Key Components:
- `IFileSystem` - Component interface
- `File` - Leaf component
- `Folder` - Composite component

#### Operations:
- List directory contents (`ls`)
- Display entire tree structure (`openAll`)
- Calculate total size (`getSize`)
- Navigate folders (`cd`)

#### Use Cases:
- File systems
- GUI component hierarchies
- Document structures
- Organization charts

---

### 3. Template Method Pattern - ML Model Training Pipeline

**Location**: `templateMethodPattern/`

A demonstration of the **Template Method Design Pattern** through a machine learning model training framework.

#### What it demonstrates:
- Template method pattern for algorithm skeletons
- Defining fixed algorithm steps in base class
- Allowing subclass-specific implementations
- Avoiding code duplication in similar algorithms

#### Key Components:
- `ModelTrainer` - Abstract base class with template method
- `NeuralNetworkTrainer` - Neural network specific implementation
- `DecisionTreeTrainer` - Decision tree specific implementation

#### Algorithm Steps:
- Load data (common)
- Preprocess data (common)
- Train model (algorithm-specific)
- Evaluate model (algorithm-specific)
- Save model (common with optional override)

#### Use Cases:
- ML model training pipelines
- Software test frameworks (setup-test-teardown)
- Database operations (open-query-close)
- File processing (open-read-process-close)

---

### 3. Music Player System

**Location**: `musicPlayerSystem/MusicPlayerApplication/`

A comprehensive music player implementation demonstrating **5 major design patterns**:

#### Design Patterns Implemented:

1. **Singleton Pattern**
   - Ensures single instance of critical components
   - Thread-safe initialization
   - Components: MusicPlayerApplication, MusicPlayerFacade, managers

2. **Strategy Pattern**
   - Encapsulates playback algorithms
   - Runtime strategy selection
   - Strategies: Sequential, Random, Custom Queue

3. **Adapter Pattern**
   - Bridges external device APIs
   - Uniform interface for different devices
   - Adapters: Bluetooth, Wired, Headphones

4. **Facade Pattern**
   - Simplifies complex subsystems
   - Clean, unified API
   - Component: MusicPlayerFacade

5. **Factory Pattern**
   - Centralizes object creation
   - Factories: DeviceFactory, StrategyFactory

#### Key Features:
- **Song Library Management**: Create and manage songs
- **Playlist Management**: Organize songs into playlists
- **Multiple Playback Strategies**: Sequential, Random, Custom Queue
- **Device Support**: Bluetooth, Wired Speakers, Headphones
- **Playback Controls**: Play, Pause, Resume, Next, Previous
- **Thread-safe Singletons**: Safe in multi-threaded environments

#### Architecture:
```
MusicPlayerApplication (Entry Point)
    ↓
MusicPlayerFacade (Orchestrator)
    ├── AudioEngine (Playback)
    ├── DeviceManager (Devices)
    ├── PlaylistManager (Collections)
    └── StrategyManager (Algorithms)
```

#### Components:

**Core:**
- `MusicPlayerApplication` - Main application (Singleton)
- `MusicPlayerFacade` - Main interface (Facade + Singleton)
- `AudioEngine` - Playback engine

**Managers (All Singletons):**
- `DeviceManager` - Device connection management
- `PlaylistManager` - Playlist collection management
- `StrategyManager` - Strategy management

**Models:**
- `Song` - Song entity (title, artist, path)
- `Playlist` - Playlist container

**Strategies:**
- `IPlayStrategy` - Strategy interface
- `SequentialPlayStrategy` - Play in order
- `RandomPlayStrategy` - Random playback
- `CustomQueueStrategy` - User-defined order

**Device Management:**
- `IAudioOutputDevice` - Device interface
- `BluetoothSpeakerAdaptor` - Bluetooth adapter
- `WiredSpeakerAdaptor` - Wired speaker adapter
- `HeadphonesAdaptor` - Headphones adapter

**Factories:**
- `DeviceFactory` - Creates devices
- `StrategyFactory` - Creates strategies

#### Usage Example:

```java
// Initialize application
MusicPlayerApplication app = MusicPlayerApplication.getInstance();

// Add songs
app.createSongInLibrary("Kesariya", "Arijit Singh", "/music/kesariya.mp3");
app.createSongInLibrary("Chaiyya Chaiyya", "Sukhwinder Singh", "/music/chaiyya.mp3");

// Create playlist
app.createPlaylist("Bollywood Vibes");
app.addSongToPlaylist("Bollywood Vibes", "Kesariya");
app.addSongToPlaylist("Bollywood Vibes", "Chaiyya Chaiyya");

// Connect to device
app.connectToAudioDevice(DeviceType.BLUETOOTH);

// Play with sequential strategy
app.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
app.loadPlaylist("Bollywood Vibes");
app.playAllTracksInPlaylist();

// Play with custom queue
app.selectPlayStrategy(PlayStrategyType.CUSTOM_QUEUE);
app.QueuesNextSong("Kesariya");
app.QueuesNextSong("Chaiyya Chaiyya");
app.playAllTracksInPlaylist();
```

---

## Design Patterns Reference

### Patterns Used Across Projects

| Pattern | Purpose | Location | Benefits |
|---------|---------|----------|----------|
| **Composite** | Compose objects into tree structures | composite_pattern/ | Treat parts and wholes uniformly |
| **Template Method** | Define algorithm skeleton | templateMethodPattern/ | Code reuse, enforced structure |
| **Singleton** | Ensure single instance | musicPlayerSystem/ | Centralized access, thread-safe |
| **Strategy** | Encapsulate algorithms | musicPlayerSystem/ | Runtime algorithm selection |
| **Adapter** | Unify incompatible interfaces | musicPlayerSystem/ | API integration, loose coupling |
| **Facade** | Simplify complex systems | musicPlayerSystem/ | Cleaner client interface |
| **Factory** | Decouple object creation | musicPlayerSystem/ | Flexible instantiation |

### SOLID Principles Applied

1. **Single Responsibility** - Each class has one reason to change
2. **Open/Closed** - Open for extension, closed for modification
3. **Liskov Substitution** - Substitutable implementations
4. **Interface Segregation** - Focused, lean interfaces
5. **Dependency Inversion** - Depend on abstractions, not concrete classes

---

## Project Structure

```
LLD/
├── composite_pattern/
│   ├── main.java                          # Entry point
│   ├── README.md                          # Composite pattern documentation
│   └── filesystem/
│       ├── IFileSystem.java               # Component interface
│       ├── File.java                      # Leaf component
│       └── Folder.java                    # Composite component
│
├── templateMethodPattern/
│   ├── main.java                          # Entry point
│   ├── README.md                          # Template method pattern documentation
│   └── trainers/
│       ├── ModelTrainer.java              # Abstract base class
│       ├── NeuralNetworkTrainer.java      # Neural network implementation
│       └── DecisionTreeTrainer.java       # Decision tree implementation
│
└── musicPlayerSystem/
    └── MusicPlayerApplication/
        ├── Main.java                      # Entry point with demo
        ├── MusicPlayerApplication.java    # Singleton
        ├── MusicPlayerFacade.java         # Facade
        ├── README.md                      # Main documentation
        ├── core/
        │   └── AudioEngine.java
        ├── device/
        │   ├── IAudioOutputDevice.java
        │   ├── BluetoothSpeakerAdaptor.java
        │   ├── WiredSpeakerAdaptor.java
        │   └── HeadphonesAdaptor.java
        ├── enums/
        │   ├── DeviceType.java
        │   └── PlayStrategyType.java
        ├── external/
        │   ├── BluetoothSpeakerApi.java
        │   ├── WiredSpeakerAPI.java
        │   └── HeadphonesAPI.java
        ├── factories/
        │   ├── DeviceFactory.java
        │   └── StrategyFactory.java
        ├── managers/
        │   ├── DeviceManager.java
        │   ├── PlaylistManager.java
        │   └── StrategyManager.java
        ├── models/
        │   ├── Song.java
        │   └── Playlist.java
        ├── strategies/
        │   ├── IPlayStrategy.java
        │   ├── SequentialPlayStrategy.java
        │   ├── RandomPlayStrategy.java
        │   └── CustomQueueStrategy.java
        └── docs/
            ├── UML_CLASS_DIAGRAM.md
            └── ARCHITECTURE_DIAGRAMS.md
```

---

## Building and Running

### Prerequisites
- Java 8 or higher
- Javac compiler

### Composite Pattern

```bash
# Navigate to composite pattern directory
cd composite_pattern/

# Compile
javac main.java filesystem/*.java

# Run
java composite_pattern.Main
```

**Expected Output:**
```
root
 file1.txt
 file2.txt
 docs/
  resume.pdf
  notes.txt
 images/
  photo.jpg

+ root
    file1.txt
    file2.txt
    + docs
        resume.pdf
        notes.txt
    + images
        photo.jpg

docs
  resume.pdf
  notes.txt

4 (total size in bytes)
```

**What this demonstrates:**
- Building hierarchical structures with composite pattern
- `ls()` - Lists immediate children
- `openAll()` - Displays complete tree structure
- `getSize()` - Calculates total size recursively
- `cd()` - Navigates folders

### Template Method Pattern

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile
javac templateMethodPattern/main.java \
       templateMethodPattern/trainers/ModelTrainer.java \
       templateMethodPattern/trainers/NeuralNetworkTrainer.java \
       templateMethodPattern/trainers/DecisionTreeTrainer.java

# Run
java templateMethodPattern.Main
```

**Expected Output:**
```
=== Neural Network Training ===
[common] Loading data from: data/images/...
[Common] Splitting into train/test and normalizing
[NeuralNet] Training Neural Network for 100 epochs
[NeuralNet] Evaluating accuracy and loss on validation set
[NeuralNet] Serializing network weights to .h5 file

=== Decision Tree Training ===
[common] Loading data from: data/iris.csv...
[Common] Splitting into train/test and normalizing
[DecisionTree] Building decision tree with max_depth=5
[DecisionTree] Computing classification report (precision/recall)
[Common] Saving model to disk as default format
```

**What this demonstrates:**
- Template method pattern enforces algorithm structure
- Base class defines sequence: load → preprocess → train → evaluate → save
- Subclasses implement algorithm-specific steps (train, evaluate)
- Common steps reused across different model trainers
- Optional override of default implementation (saveModel)

### Music Player System

```bash
# Navigate to music player directory
cd musicPlayerSystem/MusicPlayerApplication/

# Compile
javac Main.java *.java */*.java

# Run
java musicPlayerSystem.MusicPlayerApplication.Main
```

**Output:**
```
Connected to Bluetooth Speaker...
Playing Zinda
Pausing Zinda
Resuming Zinda

-- Sequential Playback --
Playing Kesariya
Playing Chaiyya Chaiyya
...

-- Random Playback --
...

-- Custom Queue Playback --
...
```

---

## 🚀 Quick Start

### To understand the Composite Pattern:
1. Read [`composite_pattern/README.md`](composite_pattern/README.md) - Pattern explanation
2. Review the UML diagram in the README
3. Run the main class and observe output
4. Examine the code:
   - `composite_pattern/main.java` - Entry point
   - `composite_pattern/filesystem/IFileSystem.java` - Component interface
   - `composite_pattern/filesystem/File.java` - Leaf node
   - `composite_pattern/filesystem/Folder.java` - Composite node

### To understand the Template Method Pattern:
1. Read [`templateMethodPattern/README.md`](templateMethodPattern/README.md) - Pattern explanation with diagrams
2. Study the UML class diagram showing abstract base and concrete implementations
3. Review the sequence diagrams showing execution flow
4. Run the main class and observe how different trainers follow the same pipeline
5. Examine the code:
   - `templateMethodPattern/main.java` - Entry point with demonstrations
   - `templateMethodPattern/trainers/ModelTrainer.java` - Abstract base class defining template
   - `templateMethodPattern/trainers/NeuralNetworkTrainer.java` - Neural network implementation
   - `templateMethodPattern/trainers/DecisionTreeTrainer.java` - Decision tree implementation

### To understand the Music Player System:
1. Read [`musicPlayerSystem/MusicPlayerApplication/README.md`](musicPlayerSystem/MusicPlayerApplication/README.md) - System overview
2. View UML diagrams in [`docs/ARCHITECTURE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)
3. Study sequence flows in [`docs/SEQUENCE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)
4. Run the main class to see it in action
5. Explore the code structure

### Complete Documentation Index:
- **[DOCUMENTATION_GUIDE.md](DOCUMENTATION_GUIDE.md)** - Navigation guide for all docs
- **[composite_pattern/README.md](composite_pattern/README.md)** - Composite pattern details
- **[templateMethodPattern/README.md](templateMethodPattern/README.md)** - Template method pattern with UML and sequence diagrams
- **[musicPlayerSystem/MusicPlayerApplication/README.md](musicPlayerSystem/MusicPlayerApplication/README.md)** - Music player system
- **[musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)** - Architecture deep dive
- **[musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)** - Detailed interactions

---

### After studying these projects, you will understand:

1. **Structural Patterns**
   - How to build complex hierarchies elegantly (Composite)
   - Adapter pattern for integration
   - Facade pattern for simplification

2. **Behavioral Patterns**
   - Template method pattern for algorithm structure
   - Strategy pattern for algorithm selection
   - Runtime behavior modification

3. **Creational Patterns**
   - Singleton instantiation and thread safety
   - Factory pattern for object creation

4. **Architecture**
   - Separation of concerns
   - Component interaction
   - System design decisions
   - Algorithm structuring

5. **Best Practices**
   - SOLID principles
   - Code maintainability
   - Extensibility patterns
   - Template method for consistent workflows

---

## Diagrams

### Composite Pattern Class Diagram
```
IFileSystem (interface)
├── +ls(indent)
├── +openAll(indent)
├── +getSize()
├── +cd(name)
├── +getName()
└── +isFolder()
    │
    ├── File (leaf)
    │   ├── -name: String
    │   ├── -size: int
    │   └── (returns false for isFolder)
    └── Folder (composite)
        ├── -name: String
        ├── -children: List<IFileSystem>
        └── (delegates operations to children)
```

**Key Characteristic**: Both File and Folder implement the same interface, allowing uniform treatment of single objects and compositions.

### Music Player System Architecture
```
MusicPlayerApplication (Singleton)
    ↓ orchestrates through
MusicPlayerFacade (Facade + Singleton)
    ├── AudioEngine (core) - Manages playback
    ├── DeviceManager (Singleton) - Manages connected devices
    ├── PlaylistManager (Singleton) - Manages playlists
    └── StrategyManager (Singleton) - Manages play strategies
        ├── Strategy: Sequential/Random/Custom
        ├── Devices: Bluetooth/Wired/Headphones
        └── Models: Song, Playlist
```

### Documentation Cross-References:
See detailed diagrams and explanations in:
- **Composite Pattern**: [`composite_pattern/README.md`](composite_pattern/README.md)
  - UML class diagram with all methods
  - Usage examples
  - Tree operations explanation

- **Music Player System**: [`musicPlayerSystem/MusicPlayerApplication/README.md`](musicPlayerSystem/MusicPlayerApplication/README.md)
  - Complete system architecture
  - 5 design patterns explained with examples
  - Mermaid UML with all 25+ classes
  - 2 sequence diagrams

- **Architecture Details**: [`musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)
  - Full UML class diagram
  - Component diagram
  - Pattern application map
  - Dependency graph
  - Object creation sequences

- **Interaction Flows**: [`musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)
  - 7 complete sequence diagrams
  - Play song flow
  - Device connection flow
  - Strategy loading flow
  - Pause/resume mechanics

---

## Future Enhancements

### Composite Pattern
- Permission system for file operations
- File metadata (created, modified dates)
- Copy/move operations
- Search functionality

### Music Player System
- Repeat modes (Repeat All, Repeat One)
- Volume control
- Equalizer support
- Playback history
- Rating system
- Shuffle with seed
- Social sharing
- Concurrent playback on multiple devices

---

## 📝 Project Comparison Matrix

| Aspect | Composite Pattern | Template Method | Music Player System |
|--------|------------------|------------------|---------------------|
| **Focus** | Structural pattern | Behavioral pattern | Multiple patterns |
| **Complexity** | Beginner-friendly | Beginner-Intermediate | Intermediate-Advanced |
| **Main Pattern** | Composite | Template Method | Singleton + Strategy + Adapter + Facade + Factory |
| **Key Learning** | Tree structures, recursive operations | Algorithm structure, code reuse | Full system design, pattern coordination |
| **Lines of Code** | ~150 | ~100 | ~2000+ |
| **Classes** | 3 core | 3 core | 25+ |
| **Interfaces** | 1 | 1 | 4+ |
| **Real-world Use** | File systems, UI hierarchies | ML pipelines, test frameworks | Music/streaming apps, audio systems |
| **Difficulty** | ⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Learning Time** | 30 minutes | 30-45 minutes | 2-3 hours |

---

## 📚 Learning Outcomes

### After studying Composite Pattern:
✅ Understand tree structures and hierarchies
✅ Know when and how to use composite pattern
✅ Implement uniform interfaces for different objects
✅ Master recursive operations
✅ Recognize real-world applications
✅ Build elegant part-whole hierarchies

### After studying Template Method Pattern:
✅ Understand behavioral patterns and algorithm structuring
✅ Define reusable algorithm skeletons
✅ Implement abstract and concrete methods effectively
✅ Know when to use final and abstract keywords
✅ Avoid code duplication in similar algorithms
✅ Create extensible frameworks
✅ Master the difference between inheritance and composition

### After studying Music Player System:
✅ Master multiple design patterns and their interactions
✅ Design complex systems with clear architecture
✅ Apply SOLID principles in practice
✅ Understand singleton thread safety
✅ Implement strategy pattern for algorithm selection
✅ Use adapter pattern for external APIs
✅ Create facade interfaces for complex systems
✅ Design maintainable and extensible code
✅ Manage complex component interactions

### Combined Learning Path:
1. **Composite Pattern** (30 min) → Understand structural patterns and tree composition
2. **Template Method Pattern** (30-45 min) → Learn behavioral patterns and algorithm structure
3. **Music Player System** (2-3 hours) → See multiple patterns working together
4. **Advanced Architecture** → Learn how to coordinate complex patterns
5. **System Design** → Apply learnings to real-world problems

---

## Key Learnings
- Patterns provide tested solutions to common problems
- They improve code maintainability and extensibility
- They facilitate team communication through shared vocabulary

### Architecture Matters
- Good architecture enables easy addition of features
- Separation of concerns keeps code manageable
- Clear dependencies make debugging simpler

### Interface Design Matters
- Well-designed interfaces are flexible and extensible
- Adapters bridge incompatible interfaces
- Facades simplify complex subsystems

### Singleton Matters
- Critical for resource management
- Thread-safe initialization prevents bugs
- Centralized access simplifies management

---

## Resources

### Design Patterns
- **Gang of Four** - Design Patterns: Elements of Reusable Object-Oriented Software
- **Head First Design Patterns** - Eric Freeman & Elisabeth Freeman

### Java Best Practices
- **Effective Java** - Joshua Bloch
- **Clean Code** - Robert C. Martin

### Architecture
- **Software Architecture in Practice** - Bass, Clements, Kazman
- **Building Microservices** - Sam Newman

---

## 📁 Complete Documentation Index

### This Repository
| File | Purpose |
|------|---------|
| [README.md](README.md) | This file - Project overview and navigation |
| [DOCUMENTATION_GUIDE.md](DOCUMENTATION_GUIDE.md) | Complete guide to all documentation |

### Composite Pattern
| File | Purpose |
|------|---------|
| [composite_pattern/README.md](composite_pattern/README.md) | Composite pattern detailed explanation |
| [composite_pattern/main.java](composite_pattern/main.java) | Runnable demo |
| [composite_pattern/filesystem/IFileSystem.java](composite_pattern/filesystem/IFileSystem.java) | Component interface |
| [composite_pattern/filesystem/File.java](composite_pattern/filesystem/File.java) | Leaf component |
| [composite_pattern/filesystem/Folder.java](composite_pattern/filesystem/Folder.java) | Composite component |

### Template Method Pattern
| File | Purpose |
|------|---------|
| [templateMethodPattern/README.md](templateMethodPattern/README.md) | Template method pattern with UML and sequence diagrams |
| [templateMethodPattern/main.java](templateMethodPattern/main.java) | Runnable demo with two trainer types |
| [templateMethodPattern/trainers/ModelTrainer.java](templateMethodPattern/trainers/ModelTrainer.java) | Abstract base class with template method |
| [templateMethodPattern/trainers/NeuralNetworkTrainer.java](templateMethodPattern/trainers/NeuralNetworkTrainer.java) | Neural network specific implementation |
| [templateMethodPattern/trainers/DecisionTreeTrainer.java](templateMethodPattern/trainers/DecisionTreeTrainer.java) | Decision tree specific implementation |

### Music Player System
| File | Purpose |
|------|---------|
| [musicPlayerSystem/MusicPlayerApplication/README.md](musicPlayerSystem/MusicPlayerApplication/README.md) | System overview with UML |
| [musicPlayerSystem/MusicPlayerApplication/Main.java](musicPlayerSystem/MusicPlayerApplication/Main.java) | Runnable demo |
| [musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md) | Architecture deep dive |
| [musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md) | 7 detailed sequence diagrams |

---

## Revision History

| Version | Date | Changes |
|---------|------|---------|
| 1.2 | April 18, 2026 | Added Template Method Pattern (ML training pipeline) with UML and sequence diagrams |
| 1.1 | April 18, 2026 | Enhanced documentation with detailed output, quick start guide, and comprehensive diagram references |
| 1.0 | April 18, 2026 | Initial implementation with comprehensive documentation and all design patterns |

---

## Repository Information

- **Owner**: prthbdhr
- **Repository**: LLD
- **Branch**: main
- **Study Focus**: Low Level Design Patterns and Architecture

---

## Contributing

This is a learning repository showcasing design patterns and low-level design principles. Each project is self-contained and demonstrates specific patterns.

For improvements or corrections, please ensure:
1. Code follows existing patterns
2. Documentation is updated
3. Examples are clear and complete
4. SOLID principles are maintained

---

## License

Educational material - Feel free to use for learning purposes.

---

**Last Updated**: April 18, 2026
**Version**: 1.2
**Documentation Status**: ✅ Complete with 3 Design Pattern Projects and Comprehensive Diagrams
