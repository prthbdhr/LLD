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

### 3. Proxy Pattern - Multiple Proxy Types Implementation

**Location**: `proxyPattern/`

A comprehensive demonstration of the **Proxy Design Pattern** through three different proxy types for controlling access to objects. The proxy acts as a placeholder or surrogate for another object to control access to it.

#### What it demonstrates

- ✅ Virtual Proxy pattern for lazy loading and deferred initialization
- ✅ Protection Proxy pattern for access control and security
- ✅ Remote Proxy pattern for remote resource access and network transparency
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

- `ISensitiveDataService` - Subject interface for sensitive operations
- `RealSensitiveDataService` - Real service handling sensitive data
- `SensitiveDataServiceProxy` - Proxy controlling access based on user roles
- **How it works**: Checks user permissions before delegating to real service
- **Security**: Only authorized roles (ADMIN, MANAGER) can access sensitive data

**Remote Proxy (Remote Resources)**:

- `IDataService` - Subject interface for data operations
- `RealDataService` - Real remote service (simulates network latency)
- `DataServiceProxy` - Proxy managing remote connections and caching
- **How it works**: Lazy connection establishment, reuses connections for efficiency
- **Transparency**: Remote objects appear local to client code

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

```text
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

### 5. Chain of Responsibility Pattern - Thread-Safe ATM Money Dispensing

**Location**: `chain_of_responsibility/`

A comprehensive demonstration of the **Chain of Responsibility Design Pattern** with **production-grade thread-safety** using **ReentrantLock**. This pattern allows passing requests along a chain of handlers where each handler decides whether to process the request or pass it to the next handler.

#### What it demonstrates

- ✅ Chain of Responsibility pattern for sequential request processing
- ✅ Handler chain architecture (1000 → 500 → 200 → 100)
- ✅ Greedy algorithm for optimal note dispensing
- ✅ ReentrantLock for efficient thread-safe access (better than synchronized)
- ✅ Comparison: Synchronized vs ReentrantLock performance
- ✅ Advanced locking techniques: fair locks, timeouts, non-blocking access
- ✅ Concurrent access handling with multiple withdrawal threads

#### Real-World Scenario

An ATM system that dispenses cash in multiple denominations:

1. **Request Processing**: User requests ₹4000 withdrawal
2. **Handler Chain**: Request flows through handlers
   - ThousandHandler: Dispenses 3×₹1000 notes, passes ₹1000 remainder
   - FiveHundredHandler: Dispenses 2×₹500 notes, passes ₹0 remainder
3. **Termination**: Request fulfilled or "insufficient funds" error

#### Key Components

**Handlers:**
- `IMoneyHandler` - Abstract base with ReentrantLock (not synchronized)
- `ThousandHandler` - Handles ₹1000 notes
- `FiveHundredHandler` - Handles ₹500 notes
- `TwoHundredHandler` - Handles ₹200 notes
- `HundredHandler` - Handles ₹100 notes

**Models:**
- `CurrencyDenomination` - Type-safe enum for denominations

**Demos:**
- `COR.java` - Basic chain demonstration
- `ThreadSafeDemo.java` - 4 concurrent users withdrawing simultaneously
- `AdvancedReentrantLockDemo.java` - Fair lock ordering with FIFO thread queueing
- `TimeoutLockDemo.java` - Timeout-based lock acquisition
- `BenchmarkDemo.java` - Stress test with 10 threads, 1000 operations

#### Benefits of This Implementation

1. **Efficient Thread-Safety** - ReentrantLock outperforms synchronized under contention
2. **Non-Blocking Operations** - `tryLock()` for optional acquisition
3. **Timeout Support** - `tryLock(long, TimeUnit)` for responsive systems
4. **Fair Lock Strategy** - Prevents thread starvation with FIFO ordering
5. **Granular Control** - Lock/unlock in different methods if needed
6. **Better Performance** - 20%+ faster than synchronized under high contention

#### ReentrantLock vs Synchronized

| Feature | Synchronized | ReentrantLock |
|---------|-------------|---------------|
| **Basic Locking** | ✓ | ✓ |
| **Fair Ordering** | ✗ | ✓ (optional) |
| **tryLock()** | ✗ | ✓ |
| **tryLock(timeout)** | ✗ | ✓ |
| **Interruptible** | ✗ | ✓ |
| **Performance** | Good | Better |
| **Complexity** | Simple | Moderate |

#### Real-World Use Cases

- **ATM Systems** - Cash dispensing with concurrent users
- **Queue Processing** - Fair task distribution with FIFO ordering
- **Resource Allocation** - Non-blocking access patterns
- **Middleware Processing** - Interruptible request chains
- **Event Handling** - Multiple handlers processing events sequentially

#### Design Principles Applied

- 🔹 **Single Responsibility** - Each handler manages one denomination
- 🔹 **Open/Closed** - Easy to add new denominations without modifying existing code
- 🔹 **Liskov Substitution** - All handlers interchangeable through IMoneyHandler
- 🔹 **Dependency Inversion** - Depend on abstract handler, not concrete implementations

---

## Design Patterns Reference

### Patterns Used Across Projects

| Pattern              | Purpose                              | Location                     | Benefits                          |
|----------------------|--------------------------------------|------------------------------|-----------------------------------|
| **Composite**        | Compose objects into tree structures | composite_pattern/           | Treat parts and wholes uniformly  |
| **Template Method**  | Define algorithm skeleton            | templateMethodPattern/       | Code reuse, enforced structure    |
| **Virtual Proxy**    | Lazy loading of expensive objects    | proxyPattern/virtualProxy/   | Deferred creation, performance    |
| **Protection Proxy** | Control access to resources          | proxyPattern/protectedProxy/ | Access control, security          |
| **Remote Proxy**     | Represent remote objects             | proxyPattern/remote/         | Distributed systems, transparency |
| **Singleton**        | Ensure single instance               | musicPlayerSystem/           | Centralized access, thread-safe   |
| **Strategy**         | Encapsulate algorithms               | musicPlayerSystem/           | Runtime algorithm selection       |
| **Adapter**          | Unify incompatible interfaces        | musicPlayerSystem/           | API integration, loose coupling   |
| **Facade**           | Simplify complex systems             | musicPlayerSystem/           | Cleaner client interface          |
| **Factory**          | Decouple object creation             | musicPlayerSystem/           | Flexible instantiation            |
| **Chain of Responsibility** | Pass requests along a handler chain | chain_of_responsibility/     | Decouple sender from receiver     |
| **Chain of Responsibility** | Pass requests along a handler chain | chain_of_responsibility/     | Decouple sender from receiver     |

### SOLID Principles Applied

1. **Single Responsibility** - Each class has one reason to change
2. **Open/Closed** - Open for extension, closed for modification
3. **Liskov Substitution** - Substitutable implementations
4. **Interface Segregation** - Focused, lean interfaces
5. **Dependency Inversion** - Depend on abstractions, not concrete classes

---

## Project Structure

```text
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
│   │   ├── Main.java                      # Entry point for protection proxy demo
│   │   ├── README.md                      # Protection proxy pattern documentation
│   │   ├── ISensitiveDataService.java     # Subject interface
│   │   ├── RealSensitiveDataService.java  # Real service with sensitive data
│   │   └── SensitiveDataServiceProxy.java # Protection proxy (access control)
│   └── remote/
│       ├── Main.java                      # Entry point for remote proxy demo
│       ├── README.md                      # Remote proxy pattern documentation
│       ├── Data/
│       │   ├── IDataService.java          # Subject interface
│       │   ├── Data.java                  # Data transfer object
│       │   ├── RealDataService.java       # Real remote service
│       │   └── DataServiceProxy.java      # Remote proxy (connection management)
│       └── Data.java                      # (duplicate - can be removed)
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
│
└── chain_of_responsibility/
    ├── COR.java                          # Basic demo
    ├── ThreadSafeDemo.java               # Concurrent access demo
    ├── AdvancedReentrantLockDemo.java    # Fair lock demo
    ├── TimeoutLockDemo.java              # Timeout-based locking demo
    ├── BenchmarkDemo.java                # Performance testing
    ├── README.md                         # Chain of Responsibility documentation
    ├── handlers/
    │   ├── IMoneyHandler.java            # Abstract base handler with ReentrantLock
    │   ├── ThousandHandler.java          # ₹1000 notes handler
    │   ├── FiveHundredHandler.java       # ₹500 notes handler
    │   ├── TwoHundredHandler.java        # ₹200 notes handler
    │   └── HundredHandler.java           # ₹100 notes handler
    └── enums/
        └── CurrencyDenomination.java     # Currency denominations enum
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

```text
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

```text
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

### Proxy Pattern - Protection Proxy (Access Control)

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile protection proxy
javac proxyPattern/protectionProxy/Main.java \
       proxyPattern/protectionProxy/ISensitiveDataService.java \
       proxyPattern/protectionProxy/RealSensitiveDataService.java \
       proxyPattern/protectionProxy/SensitiveDataServiceProxy.java

# Run
java proxyPattern.protectionProxy.Main
```

**Expected Output:**

```text
=============================================
Testing Protection Proxy Pattern
=============================================
Attempting access with ADMIN role:
[SensitiveDataServiceProxy] Access granted for role: ADMIN
[RealSensitiveDataService] Accessing sensitive data for user with role: ADMIN
Sensitive data: Confidential information...
---------------------------------------------
Attempting access with MANAGER role:
[SensitiveDataServiceProxy] Access granted for role: MANAGER
[RealSensitiveDataService] Accessing sensitive data for user with role: MANAGER
Sensitive data: Confidential information...
---------------------------------------------
Attempting access with USER role:
[SensitiveDataServiceProxy] Access denied for role: USER
Exception: Unauthorized access attempt by role: USER
=============================================
```

**What this demonstrates:**

- Role-based access control through proxy
- Authorized access (ADMIN, MANAGER) delegates to real service
- Unauthorized access (USER) throws SecurityException
- Proxy acts as security gatekeeper
- Real service only accessed when permissions validated

### Proxy Pattern - Remote Proxy (Network Transparency)

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile remote proxy
javac proxyPattern/remote/Main.java \
       proxyPattern/remote/Data/IDataService.java \
       proxyPattern/remote/Data/Data.java \
       proxyPattern/remote/Data/RealDataService.java \
       proxyPattern/remote/Data/DataServiceProxy.java

# Run
java proxyPattern.remote.Main
```

**Expected Output:**

```text
=============================================
First call to fetchData:
[DataServiceProxy] Initializing RealDataService...
[RealDataService] Initialized (simulating remote setup)
=============================================
Second call to fetchData:
[DataServiceProxy] Using cached RealDataService instance.
=============================================
Third call to fetchData:
[DataServiceProxy] Using cached RealDataService instance.
=============================================
```

**What this demonstrates:**

- Lazy connection establishment on first call
- Network latency simulation (5-second delay)
- Connection reuse on subsequent calls
- Transparent remote access through local interface
- Caching prevents redundant remote connections

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

```text
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

```text
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

### Chain of Responsibility Pattern - Thread-Safe ATM System

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile all chain of responsibility classes
javac chain_of_responsibility/COR.java \
       chain_of_responsibility/ThreadSafeDemo.java \
       chain_of_responsibility/AdvancedReentrantLockDemo.java \
       chain_of_responsibility/TimeoutLockDemo.java \
       chain_of_responsibility/BenchmarkDemo.java \
       chain_of_responsibility/handlers/*.java \
       chain_of_responsibility/enums/*.java

# Run basic demo
java -cp chain_of_responsibility COR

# Run thread-safe demo with 4 concurrent users
java -cp chain_of_responsibility ThreadSafeDemo

# Run advanced ReentrantLock demo with fair locking
java -cp chain_of_responsibility AdvancedReentrantLockDemo

# Run timeout-based locking demo
java -cp chain_of_responsibility TimeoutLockDemo

# Run performance benchmark
java -cp chain_of_responsibility BenchmarkDemo
```

**Expected Output (COR.java):**

```text
Dispensing amount: ₹4000
Dispensing 3 x ₹1000 notes.
Dispensing 1 x ₹500 notes.
Remaining amount of 500 cannot be fulfilled (Insufficient fund in ATM)
```

**What this demonstrates:**

- Chain of Responsibility pattern: Request passes through handler chain
- Greedy algorithm: Each handler dispenses maximum notes it can
- Delegation: Remaining amount passes to next handler
- Termination: Stops when fulfilled or no more handlers
- Thread-safe: Uses ReentrantLock for safe concurrent access
- Efficient locking: Better performance than synchronized under contention

**Why ReentrantLock instead of Synchronized?**

- **Non-blocking**: `tryLock()` for optional lock acquisition
- **Timeout support**: `tryLock(long, TimeUnit)` for timeout-aware operations
- **Fair locks**: FIFO ordering prevents thread starvation
- **Interruptible**: `lockInterruptibly()` for interruptible lock acquisition
- **Better performance**: More efficient under high contention (20+ threads)
- **Granular control**: Can release locks in different methods

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

1. **Virtual Proxy (Lazy Loading)**:
   - Read [`proxyPattern/virtualProxy/README.md`](proxyPattern/virtualProxy/README.md) - Virtual proxy pattern with UML and sequence diagrams
   - Study the 3 sequence diagrams showing lazy loading behavior
   - Run the virtual proxy demo to see objects created on-demand
   - Notice "Loading" only appears on first call, cached on subsequent calls

2. **Protection Proxy (Access Control)**:
   - Read [`proxyPattern/protectedProxy/README.md`](proxyPattern/protectedProxy/README.md) - Protection proxy pattern documentation
   - Study the sequence diagrams for authorized vs unauthorized access
   - Run the protection proxy demo to see role-based security in action
   - Observe how proxy prevents unauthorized access to sensitive data

3. **Remote Proxy (Network Transparency)**:
   - Read [`proxyPattern/remote/README.md`](proxyPattern/remote/README.md) - Remote proxy pattern documentation
   - Study the sequence diagrams showing connection management
   - Run the remote proxy demo to see lazy connection establishment
   - Notice the 5-second delay on first call (network simulation) and instant subsequent calls

4. **Examine the code structures**:
   - Virtual Proxy: `proxyPattern/virtualProxy/image/` - IImage, RealImage, ImageProxy
   - Protection Proxy: `proxyPattern/protectedProxy/` - ISensitiveDataService, RealSensitiveDataService, SensitiveDataServiceProxy
   - Remote Proxy: `proxyPattern/remote/Data/` - IDataService, Data, RealDataService, DataServiceProxy

### To understand the Music Player System

1. Read [`musicPlayerSystem/MusicPlayerApplication/README.md`](musicPlayerSystem/MusicPlayerApplication/README.md) - System overview
2. View UML diagrams in [`docs/ARCHITECTURE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)
3. Study sequence flows in [`docs/SEQUENCE_DIAGRAMS.md`](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)
4. Run the main class to see it in action
5. Explore the code structure

### To understand the Chain of Responsibility Pattern

1. Read [`chain_of_responsibility/README.md`](chain_of_responsibility/README.md) - Pattern explanation with thread-safety details
2. Study the handler chain architecture and flow
3. Compare synchronized vs ReentrantLock implementations
4. Run the demos in order:
   - `java COR` - Basic pattern demonstration
   - `java ThreadSafeDemo` - 4 concurrent users
   - `java AdvancedReentrantLockDemo` - Fair lock ordering
   - `java TimeoutLockDemo` - Timeout-based locking
   - `java BenchmarkDemo` - Performance under load (10 threads, 1000 ops)
5. Examine the code:
   - `chain_of_responsibility/handlers/IMoneyHandler.java` - Abstract base with ReentrantLock
   - `chain_of_responsibility/handlers/*Handler.java` - Concrete handler implementations
   - `chain_of_responsibility/enums/CurrencyDenomination.java` - Type-safe denominations

### Complete Documentation Index

- **[DOCUMENTATION_GUIDE.md](DOCUMENTATION_GUIDE.md)** - Navigation guide for all docs
- **[composite_pattern/README.md](composite_pattern/README.md)** - Composite pattern details
- **[templateMethodPattern/README.md](templateMethodPattern/README.md)** - Template method pattern with UML and sequence diagrams
- **[proxyPattern/virtualProxy/README.md](proxyPattern/virtualProxy/README.md)** - Virtual proxy pattern with UML and sequence diagrams
- **[proxyPattern/protectedProxy/README.md](proxyPattern/protectedProxy/README.md)** - Protection proxy pattern with access control
- **[proxyPattern/remote/README.md](proxyPattern/remote/README.md)** - Remote proxy pattern with network transparency
- **[musicPlayerSystem/MusicPlayerApplication/README.md](musicPlayerSystem/MusicPlayerApplication/README.md)** - Music player system
- **[musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md)** - Architecture deep dive
- **[musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)** - Detailed interactions
- **[chain_of_responsibility/README.md](chain_of_responsibility/README.md)** - Chain of Responsibility pattern with thread-safety analysis

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
   - Chain of Responsibility for request handling chains

3. **Creational Patterns**
   - Singleton instantiation and thread safety
   - Factory pattern for object creation

4. **Architecture**
   - Separation of concerns
   - Component interaction
   - System design decisions
   - Algorithm structuring
   - Handler chain design

5. **Best Practices**
   - SOLID principles
   - Code maintainability
   - Extensibility patterns
   - Template method for consistent workflows
   - ReentrantLock vs Synchronized trade-offs
   - Thread-safety patterns and mechanisms
   - Concurrent programming with handler chains

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

### Composite Pattern Enhancements

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

| Aspect             | Composite Pattern                     | Template Method                 | Proxy Pattern                              | Music Player System                               |
|--------------------|---------------------------------------|---------------------------------|--------------------------------------------|---------------------------------------------------|
| **Focus**          | Structural pattern                    | Behavioral pattern              | Structural pattern                         | Multiple patterns                                 |
| **Complexity**     | Beginner-friendly                     | Beginner-Intermediate           | Beginner-Intermediate                      | Intermediate-Advanced                             |
| **Main Pattern**   | Composite                             | Template Method                 | Virtual/Protection/Remote Proxy            | Singleton + Strategy + Adapter + Facade + Factory |
| **Key Learning**   | Tree structures, recursive operations | Algorithm structure, code reuse | Lazy loading, access control, transparency | Full system design, pattern coordination          |
| **Lines of Code**  | ~150                                  | ~100                            | ~200                                       | ~2000+                                            |
| **Classes**        | 3 core                                | 3 core                          | 3 core (virtual)                           | 25+                                               |
| **Interfaces**     | 1                                     | 1                               | 1                                          | 4+                                                |
| **Real-world Use** | File systems, UI hierarchies          | ML pipelines, test frameworks   | Image galleries, databases, remote APIs    | Music/streaming apps, audio systems               |
| **Difficulty**     | ⭐⭐                                    | ⭐⭐                              | ⭐⭐                                         | ⭐⭐⭐⭐⭐                                             |
| **Learning Time**  | 30 minutes                            | 30-45 minutes                   | 30-45 minutes                              | 2-3 hours                                         |

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

| File                                             | Purpose                                     |
|--------------------------------------------------|---------------------------------------------|
| [README.md](README.md)                           | This file - Project overview and navigation |
| [DOCUMENTATION_GUIDE.md](DOCUMENTATION_GUIDE.md) | Complete guide to all documentation         |

### Composite Pattern Documentation

| File                                                                                           | Purpose                                |
|------------------------------------------------------------------------------------------------|----------------------------------------|
| [composite_pattern/README.md](composite_pattern/README.md)                                     | Composite pattern detailed explanation |
| [composite_pattern/main.java](composite_pattern/main.java)                                     | Runnable demo                          |
| [composite_pattern/filesystem/IFileSystem.java](composite_pattern/filesystem/IFileSystem.java) | Component interface                    |
| [composite_pattern/filesystem/File.java](composite_pattern/filesystem/File.java)               | Leaf component                         |
| [composite_pattern/filesystem/Folder.java](composite_pattern/filesystem/Folder.java)           | Composite component                    |

### Proxy Pattern

| File                                                                                                                       | Purpose                                              |
|----------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| [proxyPattern/virtualProxy/README.md](proxyPattern/virtualProxy/README.md)                                                 | Virtual proxy pattern with UML and sequence diagrams |
| [proxyPattern/virtualProxy/Main.java](proxyPattern/virtualProxy/Main.java)                                                 | Virtual proxy demo with lazy loading                 |
| [proxyPattern/virtualProxy/image/IImage.java](proxyPattern/virtualProxy/image/IImage.java)                                 | Subject interface                                    |
| [proxyPattern/virtualProxy/image/RealImage.java](proxyPattern/virtualProxy/image/RealImage.java)                           | Real expensive object                                |
| [proxyPattern/virtualProxy/image/ImageProxy.java](proxyPattern/virtualProxy/image/ImageProxy.java)                         | Virtual proxy with lazy loading                      |
| [proxyPattern/protectionProxy/README.md](proxyPattern/protectionProxy/README.md)                                           | Protection proxy pattern with access control         |
| [proxyPattern/protectionProxy/Main.java](proxyPattern/protectionProxy/Main.java)                                           | Protection proxy demo                                |
| [proxyPattern/protectionProxy/ISensitiveDataService.java](proxyPattern/protectionProxy/ISensitiveDataService.java)         | Subject interface for sensitive operations           |
| [proxyPattern/protectionProxy/RealSensitiveDataService.java](proxyPattern/protectionProxy/RealSensitiveDataService.java)   | Real service with sensitive data                     |
| [proxyPattern/protectionProxy/SensitiveDataServiceProxy.java](proxyPattern/protectionProxy/SensitiveDataServiceProxy.java) | Protection proxy with role-based authorization       |
| [proxyPattern/remote/README.md](proxyPattern/remote/README.md)                                                             | Remote proxy pattern with network transparency       |
| [proxyPattern/remote/Main.java](proxyPattern/remote/Main.java)                                                             | Remote proxy demo                                    |
| [proxyPattern/remote/Data/IDataService.java](proxyPattern/remote/Data/IDataService.java)                                   | Subject interface for data operations                |
| [proxyPattern/remote/Data/Data.java](proxyPattern/remote/Data/Data.java)                                                   | Data transfer object                                 |
| [proxyPattern/remote/Data/RealDataService.java](proxyPattern/remote/Data/RealDataService.java)                             | Real remote service with network simulation          |
| [proxyPattern/remote/Data/DataServiceProxy.java](proxyPattern/remote/Data/DataServiceProxy.java)                           | Remote proxy with connection management              |

### Template Method Pattern Documentation

| File                                                                                                                 | Purpose                                                |
|----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|
| [templateMethodPattern/README.md](templateMethodPattern/README.md)                                                   | Template method pattern with UML and sequence diagrams |
| [templateMethodPattern/main.java](templateMethodPattern/main.java)                                                   | Runnable demo with two trainer types                   |
| [templateMethodPattern/trainers/ModelTrainer.java](templateMethodPattern/trainers/ModelTrainer.java)                 | Abstract base class with template method               |
| [templateMethodPattern/trainers/NeuralNetworkTrainer.java](templateMethodPattern/trainers/NeuralNetworkTrainer.java) | Neural network specific implementation                 |
| [templateMethodPattern/trainers/DecisionTreeTrainer.java](templateMethodPattern/trainers/DecisionTreeTrainer.java)   | Decision tree specific implementation                  |

### Music Player System

| File                                                                                                                                             | Purpose                      |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------|
| [musicPlayerSystem/MusicPlayerApplication/README.md](musicPlayerSystem/MusicPlayerApplication/README.md)                                         | System overview with UML     |
| [musicPlayerSystem/MusicPlayerApplication/Main.java](musicPlayerSystem/MusicPlayerApplication/Main.java)                                         | Runnable demo                |
| [musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/ARCHITECTURE_DIAGRAMS.md) | Architecture deep dive       |
| [musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md](musicPlayerSystem/MusicPlayerApplication/docs/SEQUENCE_DIAGRAMS.md)         | 7 detailed sequence diagrams |

---

## Revision History

| Version | Date           | Changes                                                                                                                                                                                       |
|---------|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.4     | April 27, 2026 | Complete Proxy Pattern implementations - Added Protection Proxy (access control) and Remote Proxy (network transparency) with comprehensive documentation, UML diagrams, and working examples |
| 1.3     | April 23, 2026 | Integrated detailed inner README documentation - added Proxy Pattern build instructions, comprehensive benefits/principles for all patterns, real-world scenarios                             |
| 1.2     | April 18, 2026 | Added Template Method Pattern (ML training pipeline) with UML and sequence diagrams                                                                                                           |
| 1.1     | April 18, 2026 | Enhanced documentation with detailed output, quick start guide, and comprehensive diagram references                                                                                          |
| 1.0     | April 18, 2026 | Initial implementation with comprehensive documentation and all design patterns                                                                                                               |

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

**Last Updated**: April 27, 2026
**Version**: 1.4
**Documentation Status**: ✅ Complete with 4 Design Pattern Projects, Comprehensive Diagrams, and Integrated Inner README Details
