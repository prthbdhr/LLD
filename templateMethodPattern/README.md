# Template Method Pattern - ML Model Training Pipeline

## Overview

This project demonstrates the **Template Method Design Pattern** through a machine learning model training framework. The pattern defines the skeleton of an algorithm in a base class and lets subclasses implement specific steps without changing the algorithm's structure.

---

## 🎯 Pattern Purpose

The Template Method Pattern is used when:
- You have multiple variations of an algorithm with common steps
- You want to avoid code duplication
- You need to control the order of algorithm steps
- Subclasses should only override specific parts, not the entire algorithm

---

## 📊 Real-World Scenario

Different ML models (Neural Networks, Decision Trees, Random Forests) go through the same training pipeline:

1. **Load Data** - Read dataset from files
2. **Preprocess Data** - Normalize, train-test split
3. **Train Model** - Algorithm-specific training (varies)
4. **Evaluate Model** - Calculate metrics (varies)
5. **Save Model** - Persist to disk (can be overridden)

The first two and last steps are common, but training and evaluation are model-specific.

---

## 🏗️ Class Diagram

### UML Class Diagram

```
┌─────────────────────────────────────────────────┐
│      ModelTrainer (abstract)                    │
├─────────────────────────────────────────────────┤
│ # dataPath: String                              │
├─────────────────────────────────────────────────┤
│ + trainPipeline(path): void ◄── template method│
│ # loadData(path): void                          │
│ # preprocessData(): void                        │
│ # trainModel(): void * (abstract)               │
│ # evaluateModel(): void * (abstract)            │
│ # saveModel(): void (can be overridden)         │
└─────────────────────────────────────────────────┘
           ▲                ▲
           │                │ extends 
           │ extends        │
   ┌───────┴────────────────┴────────┐
   │                                 │
┌──────────────────────┐     ┌────────────────────────┐
│ NeuralNetworkTrainer │     │ DecisionTreeTrainer    │
├──────────────────────┤     ├────────────────────────┤
│ # epochs: int = 100  │     │ # maxDepth: int = 5    │
├──────────────────────┤     ├────────────────────────┤
│ + trainModel()       │     │ + trainModel()         │
│ + evaluateModel()    │     │ + evaluateModel()      │
│ + saveModel()        │     │ (uses default save)    │
└──────────────────────┘     └────────────────────────┘
```

### Key Components

1. **ModelTrainer (Abstract Base Class)**
   - `trainPipeline(String path)` - **Final template method** defining the fixed sequence
   - `loadData()` - Common implementation
   - `preprocessData()` - Common implementation
   - `trainModel()` - **Abstract** (subclasses must implement)
   - `evaluateModel()` - **Abstract** (subclasses must implement)
   - `saveModel()` - Concrete with default implementation (optional override)

2. **NeuralNetworkTrainer (Concrete)**
   - Implements `trainModel()` with neural network training logic
   - Implements `evaluateModel()` with accuracy/loss calculation
   - Overrides `saveModel()` to save as .h5 format

3. **DecisionTreeTrainer (Concrete)**
   - Implements `trainModel()` with decision tree construction
   - Implements `evaluateModel()` with classification metrics
   - Uses inherited `saveModel()` from base class

---

## 📋 Sequence Diagram: Training Pipeline Execution

### Sequence: NeuralNetworkTrainer.trainPipeline("data/images/")

```
Client                    NeuralNetworkTrainer      ModelTrainer          System
  │                              │                        │                  │
  │──trainPipeline()────────────>│                        │                  │
  │                              │───trainPipeline()──┐  │                  │
  │                              │                    │  │                  │
  │                              │◄──calls template──┐│  │                  │
  │                              │                   ││  │                  │
  │                              │────loadData()─────┼─>│                  │
  │                              │◄─────[common]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │─preprocessData()──┼─>│                  │
  │                              │◄─────[common]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │────trainModel()───┼─>│ [Neural Network] │
  │                              │  (NeuralNet impl)  ││  │──train 100 sec─>│
  │                              │◄─────[trained]────┼──│                  │
  │                              │                   ││  │                  │
  │                              │─evaluateModel()───┼─>│ Calculate accuracy
  │                              │ (NeuralNet impl)   ││  │────evaluate────>│
  │                              │◄────[metrics]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │───saveModel()─────┼─>│ Save as .h5      │
  │                              │ (NeuralNet override)││  │────save────────>│
  │                              │◄─────[saved]──────┼──│                  │
  │                              │                    │  │                  │
  │◄─────pipeline complete──────│                    │  │                  │
  │                              │                    │  │                  │
```

### Sequence: DecisionTreeTrainer.trainPipeline("data/iris.csv")

```
Client                    DecisionTreeTrainer       ModelTrainer          System
  │                              │                        │                  │
  │──trainPipeline()────────────>│                        │                  │
  │                              │───trainPipeline()──┐  │                  │
  │                              │                    │  │                  │
  │                              │◄──calls template──┐│  │                  │
  │                              │                   ││  │                  │
  │                              │────loadData()─────┼─>│                  │
  │                              │◄─────[common]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │─preprocessData()──┼─>│                  │
  │                              │◄─────[common]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │────trainModel()───┼─>│ Build tree       │
  │                              │ (DecisionTree)     ││  │──construct tree─>│
  │                              │◄──[tree built]────┼──│                  │
  │                              │                   ││  │                  │
  │                              │─evaluateModel()───┼─>│ Classification   │
  │                              │ (DecisionTree impl)││  │──precision/rec─>│
  │                              │◄────[metrics]─────┼──│                  │
  │                              │                   ││  │                  │
  │                              │───saveModel()─────┼─>│ Default save     │
  │                              │ (inherited default) ││  │──save as generic─>
  │                              │◄─────[saved]──────┼──│                  │
  │                              │                    │  │                  │
  │◄─────pipeline complete──────│                    │  │                  │
  │                              │                    │  │                  │
```

---

## 💻 Code Components

### 1. Base Class: ModelTrainer

```java
public abstract class ModelTrainer {
    
    // Template method - FINAL so subclasses cannot override
    public final void trainPipeline(String dataPath) {
        loadData(dataPath);           // Step 1: Common
        preprocessData();              // Step 2: Common
        trainModel();                  // Step 3: Algorithm-specific
        evaluateModel();               // Step 4: Algorithm-specific
        saveModel();                   // Step 5: Common with optional override
    }
    
    // Common implementation
    protected void loadData(String path) {
        System.out.println("[common] Loading data from: " + path + "...");
    }
    
    // Common implementation
    protected void preprocessData() {
        System.out.println("[Common] Splitting into train/test and normalizing");
    }
    
    // Abstract methods - MUST be implemented by subclasses
    protected abstract void trainModel();
    protected abstract void evaluateModel();
    
    // Concrete with default - can be overridden
    protected void saveModel() {
        System.out.println("[Common] Saving model to disk as default format");
    }
}
```

### 2. Concrete Implementation: NeuralNetworkTrainer

```java
public class NeuralNetworkTrainer extends ModelTrainer {
    
    @Override
    protected void trainModel() {
        System.out.println("[NeuralNet] Training Neural Network for 100 epochs");
    }
    
    @Override
    protected void evaluateModel() {
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }
    
    @Override
    protected void saveModel() {
        System.out.println("[NeuralNet] Serializing network weights to .h5 file");
    }
}
```

### 3. Concrete Implementation: DecisionTreeTrainer

```java
public class DecisionTreeTrainer extends ModelTrainer {
    
    @Override
    protected void trainModel() {
        System.out.println("[DecisionTree] Building decision tree with max_depth=5");
    }
    
    @Override
    protected void evaluateModel() {
        System.out.println("[DecisionTree] Computing classification report (precision/recall)");
    }
    // Uses default saveModel() from base class
}
```

---

## 🚀 Usage Example

```java
// Create trainers
ModelTrainer nnTrainer = new NeuralNetworkTrainer();
ModelTrainer dtTrainer = new DecisionTreeTrainer();

// Execute same template method with different implementations
System.out.println("=== Neural Network Training ===");
nnTrainer.trainPipeline("data/images/");

System.out.println("\n=== Decision Tree Training ===");
dtTrainer.trainPipeline("data/iris.csv");
```

### Output:
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

---

## 🏃 Building and Running

### Prerequisites
- Java 8 or higher
- Javac compiler

### Compilation and Execution

```bash
# Navigate to LLD directory
cd /Users/tyrant369/Tyrant369-Macbook-Air-M3/Study/Code/system_design/lld/LLD

# Compile all files
javac templateMethodPattern/main.java \
       templateMethodPattern/trainers/ModelTrainer.java \
       templateMethodPattern/trainers/NeuralNetworkTrainer.java \
       templateMethodPattern/trainers/DecisionTreeTrainer.java

# Run the demonstration
java templateMethodPattern.Main
```

---

## 📚 Key Learning Points

### 1. **Template Method Pattern Benefits**
- ✅ **Code Reuse** - Common steps defined once in base class
- ✅ **Consistency** - Algorithm structure guaranteed across all implementations
- ✅ **Extensibility** - Easy to add new model trainers
- ✅ **Control** - Base class controls algorithm flow, not subclasses

### 2. **When to Use Template Method**
- Multiple classes have similar algorithms with slight variations
- You want to avoid code duplication in algorithm steps
- You need to enforce a specific order of operations
- You want to facilitate varying behavior in specific steps

### 3. **Design Considerations**
- Mark template method as `final` to prevent override
- Use `protected` for hook methods (abstract or with default)
- Provide sensible defaults for optional overrides
- Keep the template method simple and readable

### 4. **Comparison with Other Patterns**
- **Strategy Pattern**: Client chooses algorithm; Template Method: Sequence is predefined
- **Decorator Pattern**: Adds behavior dynamically; Template Method: Defines structure statically
- **Factory Pattern**: Creates objects; Template Method: Defines algorithm steps

---

## 🔄 Alternative Variations

### Variation 1: More Flexible with Hooks
```java
public abstract class FlexibleModelTrainer {
    public final void trainPipeline(String dataPath) {
        beforeLoadData();      // hook
        loadData(dataPath);
        afterLoadData();       // hook
        // ... more steps
    }
    
    protected void beforeLoadData() {}  // empty by default
    protected void afterLoadData() {}    // empty by default
}
```

### Variation 2: With Strategy Switch
```java
public abstract class AdaptiveModelTrainer {
    private EvaluationStrategy strategy = new DefaultEvaluation();
    
    public final void trainPipeline(String dataPath) {
        loadData(dataPath);
        preprocessData();
        trainModel();
        strategy.evaluate();  // Can switch strategy
    }
}
```

---

## 🧠 Common Mistakes

❌ **Mistake 1**: Making template method non-final (allows breaking the algorithm structure)
```java
// Bad
public void trainPipeline() { ... }  // Can be overridden!
```

✅ **Solution**: Always use `final` keyword
```java
// Good
public final void trainPipeline() { ... }  // Cannot be overridden
```

❌ **Mistake 2**: Too many abstract methods (forces unnecessary overrides)
```java
// Bad
protected abstract void setup();
protected abstract void beforeTrain();
protected abstract void train();
protected abstract void afterTrain();
```

✅ **Solution**: Only make truly algorithm-specific steps abstract
```java
// Good
protected void setup() { /* default */ }
protected abstract void train();      // Only this varies significantly
```

---

## 📈 Real-World Applications

### Machine Learning Frameworks
- **PyTorch**: Training loop (forward, backward, optimize)
- **TensorFlow**: Model pipeline (preprocessing, training, evaluation)
- **Scikit-learn**: Fit-transform pipeline

### Software Development
- **Error handling**: Try-catch-finally structure
- **Database operations**: Open-prepare-execute-close
- **File processing**: Open-read-process-close

### Testing Frameworks
- **JUnit**: Setup-test-teardown (setUp, testMethod, tearDown)
- **TestNG**: Similar lifecycle management

---

## 🔗 Related Patterns

- **Strategy Pattern** - Similar but client chooses algorithm
- **Factory Pattern** - Can be used with Template Method for object creation
- **Decorator Pattern** - Can wrap template method results
- **Observer Pattern** - Can notify observers during template steps

---

## 📝 Summary

The Template Method Pattern provides a way to define the skeleton of an algorithm in a base class while letting subclasses implement specific steps. This project demonstrates how:

1. Common training pipeline steps are defined in the base class
2. Model-specific training and evaluation are implemented by subclasses
3. The algorithm structure remains consistent across all implementations
4. Adding new model types requires minimal code changes

This pattern is fundamental to creating extensible and maintainable systems where multiple implementations share common workflows.

---

**Pattern**: Template Method
**Created**: April 18, 2026
**Complexity**: ⭐⭐ (Intermediate)
**Use Case**: ML Model Training Pipeline
