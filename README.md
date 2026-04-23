# Low Level Design (LLD) Projects

## Overview

This repository contains comprehensive implementations of various design patterns and architectural concepts used in building scalable, maintainable software systems. Each project demonstrates best practices in object-oriented design and architectural patterns.

---

## 📋 Table of Contents

1. [Projects Overview](#projects-overview) - Summary of all projects
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

A demonstration of the **Composite Design Pattern** through a hierarchical file system implementation. This pattern allows you to compose objects into tree structures to represent part-whole hierarchies, letting clients treat individual objects and compositions of objects uniformly.

#### What it demonstrates

- ✅ Composite pattern for tree structures and part-whole hierarchies
- ✅ Treating individual objects and compositions uniformly through common interfaces
- ✅ Recursive operations on hierarchical data
- ✅ Elegant abstraction enabling transparent tree traversal
- ✅ Component, Leaf, and Composite design participants

#### Key Components

- `IFileSystem` - Component interface (defines common operations)
- `File` - Leaf component (terminal nodes with no children)
- `Folder` - Composite component (can contain children, delegates operations)

#### Core Operations

- `ls(indent)` - Lists immediate children with indentation
- `openAll(indent)` - Recursively displays entire tree structure
- `getSize()` - Returns file size or sum of all children (recursive)
- `cd(name)` - Navigates to child folder or returns null

#### Benefits of Composite Pattern

1. **Uniform Interface** - Treat files and folders the same way
2. **Recursive Composition** - Build complex structures from simple components
3. **Simple Client Code** - No need to check types or handle special cases
4. **Easy to Extend** - Add new file system types easily
5. **Tree Operations** - Apply operations recursively to entire hierarchies

#### Real-World Use Cases

- **File Systems** - Directory hierarchies with files and folders
- **GUI Components** - Windows containing widgets, panels containing controls
- **Organization Hierarchies** - Company structures with departments
- **Menu Systems** - Menus with submenus and menu items
- **Document Structures** - Books with chapters, sections, subsections

#### Design Principles Applied

- 🔖 **Single Responsibility** - Each class has one purpose
- 🔖 **Open/Closed** - Easy to add new file system types without modifying existing code
- 🔖 **Liskov Substitution** - Both File and Folder usable wherever IFileSystem expected
- 🔖 **Dependency Inversion** - Classes depend on IFileSystem abstraction, not concrete types

---

### 2. Template Method Pattern - ML Model Training Pipeline

**Location**: `templateMethodPattern/`

A demonstration of the **Template Method Design Pattern** through a machine learning model training framework. This pattern defines the skeleton of an algorithm in a base class and lets subclasses implement specific steps without changing the algorithm's structure.

#### What it demonstrates

- ✅ Template method pattern for algorithm structure and skeleton definition
- ✅ Defining fixed algorithm steps in abstract base class
- ✅ Allowing subclass-specific implementations for variant steps
- ✅ Avoiding code duplication in similar algorithms
- ✅ Use of final methods, abstract methods, and concrete implementations
- ✅ Template method controlling algorithm flow

#### Real-World Scenario

Different ML models (Neural Networks, Decision Trees, Random Forests) follow the same training pipeline:

1. **Load Data** - Read dataset from files (common)
2. **Preprocess Data** - Normalize, train-test split (common)
3. **Train Model** - Algorithm-specific training (varies per model)
4. **Evaluate Model** - Calculate metrics (varies per model)
5. **Save Model** - Persist to disk (common with optional override)

#### Key Components

- `ModelTrainer` - Abstract base class with final template method
  - `trainPipeline(path)` - Final template method controlling flow
  - `loadData()` - Common implementation
  - `preprocessData()` - Common implementation
  - `trainModel()` - Abstract (must override)
  - `evaluateModel()` - Abstract (must override)
  - `saveModel()` - Concrete with default (can override)
- `NeuralNetworkTrainer` - Implements neural network specific training/evaluation
- `DecisionTreeTrainer` - Implements decision tree specific training/evaluation

#### Benefits of Template Method Pattern

1. **Code Reuse** - Common steps defined once in base class
2. **Consistency** - Algorithm structure guaranteed across all implementations
3. **Extensibility** - Easy to add new model trainers
4. **Control** - Base class controls algorithm flow, not subclasses
5. **Enforced Structure** - Subclasses cannot skip or reorder steps

#### Real-World Use Cases

- **ML Model Training** - Neural networks, decision trees, ensemble methods
- **Software Test Frameworks** - setup-test-teardown pattern
- **Database Operations** - open-query-close pattern
- **File Processing** - open-read-process-close pattern
- **Report Generation** - fetch-format-export pattern

#### Design Principles Applied

- 🔹 **Single Responsibility** - Each method has one purpose
- 🔹 **Open/Closed** - Open for extension through subclasses, closed for modification
- 🔹 **Liskov Substitution** - All trainers interchangeable through ModelTrainer interface
- 🔹 **Dependency Inversion** - Depend on abstract template, not concrete implementations

---

### 3. Proxy Pattern - Image Loading System

**Location**: `proxyPattern/`

A demonstration of the **Proxy Design Pattern** through multiple proxy types for controlling access to expensive objects. The proxy acts as a placeholder or surrogate for another object to control access to it.

#### What it demonstrates

- ✅ Virtual Proxy pattern for lazy loading and deferred initialization
- ✅ Protection Proxy pattern for access control and security
- ✅ Remote Proxy pattern for remote resource access
- ✅ Deferring expensive object creation until needed
- ✅ Transparent delegation through common interfaces
- ✅ Caching and resource management strategies

#### Proxy Types Implemented

**Virtual Proxy (Lazy Loading)** - Most Common:

- `IImage` - Subject interface (defines common operations)
- `RealImage` - Real expensive object (loads images from disk)
- `ImageProxy` - Proxy that delays RealImage creation until display() called
- **How it works**: First call creates RealImage, subsequent calls reuse cached instance
- **Performance gain**: Avoid loading large objects until actually needed

**Protection Proxy (Access Control)**:

- Controls access to sensitive resources
- Validates permissions before delegating to real object
- Useful for restricting who can access what

**Remote Proxy (Remote Resources)**:

- Represents remote objects locally
- Handles network communication transparently
- Useful for distributed systems

#### Key Benefits

1. **Lazy Initialization** - Expensive objects created on-demand, not upfront
2. **Caching** - Once loaded, object is reused (no reload on subsequent calls)
3. **Access Control** - Manage who/how objects are accessed
4. **Resource Management** - Implement resource pooling and cleanup
5. **Transparency** - Client code unaffected by proxying (same interface)

#### Real-World Use Cases

- **Image Galleries** - Load images only when viewed (major performance boost)
- **Document Editors** - Load large documents on first access
- **Database Connections** - Create connections when actually needed
- **Lazy Collections** - Load collection items on iteration
- **Remote API Calls** - Represent remote objects locally
- **Protected Resources** - Control access to sensitive data
- **Network Communication** - Manage remote service calls

#### Design Principles Applied

- 🔹 **Single Responsibility** - Proxy handles access control, real object handles functionality
- 🔹 **Open/Closed** - Can add new proxies without modifying real object
- 🔹 **Liskov Substitution** - Proxy and real object interchangeable through interface
- 🔹 **Dependency Inversion** - Depend on IImage abstraction, not concrete implementations

---

### 4. Music Player System

**Location**: `musicPlayerSystem/MusicPlayerApplication/`

A comprehensive music player implementation demonstrating **5 major design patterns**:

#### Design Patterns Implemented

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

#### Key Features

- **Song Library Management**: Create and manage songs
- **Playlist Management**: Organize songs into playlists
- **Multiple Playback Strategies**: Sequential, Random, Custom Queue
- **Device Support**: Bluetooth, Wired Speakers, Headphones
- **Playback Controls**: Play, Pause, Resume, Next, Previous
- **Thread-safe Singletons**: Safe in multi-threaded environments

#### Architecture

```
MusicPlayerApplication (Entry Point)
    ↓
MusicPlayerFacade (Orchestrator)
    ├── AudioEngine (Playback)
    ├── DeviceManager (Devices)
    ├── PlaylistManager (Collections)
    └── StrategyManager (Algorithms)
```

#### Components

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

#### Usage Example

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
| **Virtual Proxy** | Lazy loading of expensive objects | proxyPattern/virtualProxy/ | Deferred creation, performance |
| **Protection Proxy** | Control access to resources | proxyPattern/protectionProxy/ | Access control, security |
| **Remote Proxy** | Represent remote objects | proxyPattern/remoteProxy/ | Distributed systems, transparency |
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
├── proxyPattern/
│   ├── virtualProxy/
│   │   ├── Main.java                      # Entry point for virtual proxy demo
│   │   ├── README.md                      # Virtual proxy pattern documentation
│   │   └── image/
│   │       ├── IImage.java                # Subject interface
│   │       ├── RealImage.java             # Real heavy object
│   │       └── ImageProxy.java            # Virtual proxy (lazy loading)
│   ├── protectionProxy/
│   │   └── Main.java                      # Protection proxy demo
│   └── remoteProxy/
│       └── Main.java                      # Remote proxy demo
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
- `ls()` - Lists immediate children with proper indentation
- `openAll()` - Displays complete tree structure recursively
- `getSize()` - Calculates total size by recursively summing children
- `cd()` - Navigates to folders, returns null for files
- Uniform treatment of files and folders through common interface

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

### Proxy Pattern - Virtual Proxy (Lazy Loading)

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile virtual proxy
javac proxyPattern/virtualProxy/Main.java \
       proxyPattern/virtualProxy/image/IImage.java \
       proxyPattern/virtualProxy/image/RealImage.java \
       proxyPattern/virtualProxy/image/ImageProxy.java

# Run
java proxyPattern.virtualProxy.Main
```

**Expected Output:**

```
------------------------------------------------
------------------------------------------------
[RealImage] Loading image from disk: photo1.jpg
[RealImage] Displaying photo1.jpg
[RealImage] Loading image from disk: photo2.jpg
[RealImage] Displaying photo2.jpg
[RealImage] Displaying photo1.jpg
------------------------------------------------
------------------------------------------------
```

**What this demonstrates:**

- First `image1.display()` call: Creates RealImage, loads from disk
- First `image2.display()` call: Creates RealImage, loads from disk
- Second `image1.display()` call: Reuses cached RealImage (no "Loading" message)
- Lazy loading: Objects created only when needed
- Caching: Subsequent calls use cached instance
- Transparent: Client sees same interface (IImage) for both proxy and real object

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

### To understand the Composite Pattern

1. Read [`composite_pattern/README.md`](composite_pattern/README.md) - Pattern explanation
2. Review the UML diagram in the README
3. Run the main class and observe output
4. Examine the code:
   - `composite_pattern/main.java` - Entry point
   - `composite_pattern/filesystem/IFileSystem.java` - Component interface
   - `composite_pattern/filesystem/File.java` - Leaf node
   - `composite_pattern/filesystem/Folder.java` - Composite node

### To understand the Template Method Pattern

1. Read [`templateMethodPattern/README.md`](templateMethodPattern/README.md) - Pattern explanation with diagrams
2. Study the UML class diagram showing abstract base and concrete implementations
3. Review the sequence diagrams showing execution flow
4. Run the main class and observe how different trainers follow the same pipeline
5. Examine the code:
   - `templateMethodPattern/main.java` - Entry point with demonstrations
   - `templateMethodPattern/trainers/ModelTrainer.java` - Abstract base class defining template
   - `templateMethodPattern/trainers/NeuralNetworkTrainer.java` - Neural network implementation
   - `templateMethodPattern/trainers/DecisionTreeTrainer.java` - Decision tree implementation

### To understand the Proxy Pattern

1. Read [`proxyPattern/virtualProxy/README.md`](proxyPattern/virtualProxy/README.md) - Virtual proxy pattern with UML and sequence diagrams
2. Study the 3 sequence diagrams:
   - Creating proxy objects (no loading yet)
   - First display() call (lazy loads RealImage)
   - Subsequent calls (reuses cached instance)
3. Review the UML class diagram showing interface and proxy relationships
4. Run the virtual proxy demo to see lazy loading in action
   - Notice "Loading" only appears on first call
   - Notice no "Loading" message on subsequent calls (cached)
5. Examine the code:
   - `proxyPattern/virtualProxy/Main.java` - Entry point with demo
   - `proxyPattern/virtualProxy/image/IImage.java` - Subject interface
   - `proxyPattern/virtualProxy/image/RealImage.java` - Real expensive object
   - `proxyPattern/virtualProxy/image/ImageProxy.java` - Virtual proxy implementation (lazy loading)

### To understand the Music Player System

1. Read [`musicPlayerSystem/MusicPlayerApplication/README.md`](musicPlayerSystem/MusicPlayerApplication/README.md) - System overview
2. View UML diagrams in [`docs/ARCHITECTURE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)
3. Study sequence flows in [`docs/SEQUENCE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)
4. Run the main class to see it in action
5. Explore the code structure

### Complete Documentation Index

- **[DOCUMENTATION_GUIDE.md](DOCUMENTATION_GUIDE.md)** - Navigation guide for all docs
- **[composite_pattern/README.md](composite_pattern/README.md)** - Composite pattern details
- **[templateMethodPattern/README.md](templateMethodPattern/README.md)** - Template method pattern with UML and sequence diagrams
- **[proxyPattern/virtualProxy/README.md](proxyPattern/virtualProxy/README.md)** - Virtual proxy pattern with UML and sequence diagrams
- **[musicPlayerSystem/MusicPlayerApplication/README.md](musicPlayerSystem/MusicPlayerApplication/README.md)** - Music player system
- **[musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)** - Architecture deep dive
- **[musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)** - Detailed interactions

---

### After studying these projects, you will understand

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

### Documentation Cross-References

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

| Aspect | Composite Pattern | Template Method | Proxy Pattern | Music Player System |
|--------|------------------|------------------|------------------|---------------------|
| **Focus** | Structural pattern | Behavioral pattern | Structural pattern | Multiple patterns |
| **Complexity** | Beginner-friendly | Beginner-Intermediate | Beginner-Intermediate | Intermediate-Advanced |
| **Main Pattern** | Composite | Template Method | Virtual/Protection/Remote Proxy | Singleton + Strategy + Adapter + Facade + Factory |
| **Key Learning** | Tree structures, recursive operations | Algorithm structure, code reuse | Lazy loading, access control, transparency | Full system design, pattern coordination |
| **Lines of Code** | ~150 | ~100 | ~200 | ~2000+ |
| **Classes** | 3 core | 3 core | 3 core (virtual) | 25+ |
| **Interfaces** | 1 | 1 | 1 | 4+ |
| **Real-world Use** | File systems, UI hierarchies | ML pipelines, test frameworks | Image galleries, databases, remote APIs | Music/streaming apps, audio systems |
| **Difficulty** | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Learning Time** | 30 minutes | 30-45 minutes | 30-45 minutes | 2-3 hours |

---

## 📚 Learning Outcomes

### After studying Composite Pattern

✅ Understand tree structures and hierarchies
✅ Know when and how to use composite pattern
✅ Implement uniform interfaces for different objects
✅ Master recursive operations
✅ Recognize real-world applications
✅ Build elegant part-whole hierarchies

### After studying Template Method Pattern

✅ Understand behavioral patterns and algorithm structuring
✅ Define reusable algorithm skeletons
✅ Implement abstract and concrete methods effectively
✅ Know when to use final and abstract keywords
✅ Avoid code duplication in similar algorithms
✅ Create extensible frameworks
✅ Master the difference between inheritance and composition

### After studying Proxy Pattern

✅ Understand structural patterns and proxy implementations
✅ Master lazy loading and deferred initialization
✅ Implement virtual proxies for expensive objects
✅ Control object access through proxies
✅ Understand transparent proxy delegation
✅ Know when to use different proxy types
✅ Implement object caching strategies

### After studying Music Player System

✅ Master multiple design patterns and their interactions
✅ Design complex systems with clear architecture
✅ Apply SOLID principles in practice
✅ Understand singleton thread safety
✅ Implement strategy pattern for algorithm selection
✅ Use adapter pattern for external APIs
✅ Create facade interfaces for complex systems
✅ Design maintainable and extensible code
✅ Manage complex component interactions

### Combined Learning Path

1. **Composite Pattern** (30 min) → Understand structural patterns and tree composition
2. **Template Method Pattern** (30-45 min) → Learn behavioral patterns and algorithm structure
3. **Proxy Pattern** (30-45 min) → Understand proxy patterns and lazy loading
4. **Music Player System** (2-3 hours) → See multiple patterns working together
5. **Advanced Architecture** → Learn how to coordinate complex patterns
6. **System Design** → Apply learnings to real-world problems

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

### Proxy Pattern

| File | Purpose |
|------|----------|
| [proxyPattern/virtualProxy/README.md](proxyPattern/virtualProxy/README.md) | Virtual proxy pattern with UML and sequence diagrams |
| [proxyPattern/virtualProxy/Main.java](proxyPattern/virtualProxy/Main.java) | Virtual proxy demo with lazy loading |
| [proxyPattern/virtualProxy/image/IImage.java](proxyPattern/virtualProxy/image/IImage.java) | Subject interface |
| [proxyPattern/virtualProxy/image/RealImage.java](proxyPattern/virtualProxy/image/RealImage.java) | Real expensive object |
| [proxyPattern/virtualProxy/image/ImageProxy.java](proxyPattern/virtualProxy/image/ImageProxy.java) | Virtual proxy with lazy loading |
| [proxyPattern/protectionProxy/Main.java](proxyPattern/protectionProxy/Main.java) | Protection proxy demo |
| [proxyPattern/remoteProxy/Main.java](proxyPattern/remoteProxy/Main.java) | Remote proxy demo |

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
|---------|------|---------|| 1.3 | April 23, 2026 | Integrated detailed inner README documentation - added Proxy Pattern build instructions, comprehensive benefits/principles for all patterns, real-world scenarios || 1.2 | April 18, 2026 | Added Template Method Pattern (ML training pipeline) with UML and sequence diagrams |
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

**Last Updated**: April 23, 2026
**Version**: 1.3
**Documentation Status**: ✅ Complete with 4 Design Pattern Projects, Comprehensive Diagrams, and Integrated Inner README Details
