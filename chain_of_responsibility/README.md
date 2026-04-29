# Chain of Responsibility Design Pattern - Thread-Safe ATM System

## Overview

This implementation demonstrates the **Chain of Responsibility pattern** with **ReentrantLock-based thread safety** through an ATM money dispensing system. The system efficiently handles multiple concurrent withdrawal requests.

## File Structure

``` 
chain_of_responsibility/
├── COR.java                          # Basic demo
├── ThreadSafeDemo.java               # Synchronized version demo
├── AdvancedReentrantLockDemo.java    # ReentrantLock with fair locking
├── TimeoutLockDemo.java              # Timeout-based lock handling
├── BenchmarkDemo.java                # Performance comparison
├── handlers/                         # Handler classes package
│   ├── IMoneyHandler.java            # Abstract base with ReentrantLock
│   ├── ThousandHandler.java          # ₹1000 note handler
│   ├── FiveHundredHandler.java       # ₹500 note handler
│   ├── TwoHundredHandler.java        # ₹200 note handler
│   └── HundredHandler.java           # ₹100 note handler
├── enums/                            # Enum classes package
│   └── CurrencyDenomination.java     # Currency denominations
└── README.md                         # This documentation
```

## Thread-Safety Implementation

### ReentrantLock vs Synchronized

The system uses **ReentrantLock** instead of `synchronized` for superior performance and control:

| Feature                 | Synchronized | ReentrantLock |
|-------------------------|--------------|---------------|
| **Basic Locking**       | ✓            | ✓             |
| **Fair Lock Ordering**  | ✗            | ✓             |
| **tryLock()**           | ✗            | ✓             |
| **tryLock(timeout)**    | ✗            | ✓             |
| **Interruptible**       | ✗            | ✓             |
| **Performance**         | Good         | Better        |
| **Condition Variables** | ✗            | ✓             |

### Key Components

#### 1. **IMoneyHandler (Abstract Base)**

```java
protected final ReentrantLock lock = new ReentrantLock();

public void setNextHandler(IMoneyHandler nexHandler) {
    lock.lock();
    try {
        this.nextHandler = nexHandler;
    } finally {
        lock.unlock();
    }
}
```

#### 2. **Handler Classes (All 4 handlers)**

```java
private final ReentrantLock dispenseLock = new ReentrantLock();

@Override
public void dispense(int amount) {
    dispenseLock.lock();
    try {
        // Critical section with state modifications
        int notesNeeded = amount / 1000;
        numNotes -= notesNeeded;
        // ... rest of logic
    } finally {
        dispenseLock.unlock();
    }
}
```

## Demos

### 1. **COR.java** - Basic Demo

Simple demonstration of the chain of responsibility pattern.

```bash
javac COR.java handlers/*.java enums/*.java
java COR
```

### 2. **ThreadSafeDemo.java** - Concurrent Access

Shows how 4 users can safely withdraw from the ATM simultaneously.

```bash
javac ThreadSafeDemo.java handlers/*.java enums/*.java
java ThreadSafeDemo
```

### 3. **AdvancedReentrantLockDemo.java** - Fair Locking

Demonstrates fair lock ordering with FIFO thread queueing.

```bash
javac AdvancedReentrantLockDemo.java handlers/*.java enums/*.java
java AdvancedReentrantLockDemo
```

**Features:**

- Fair lock strategy prevents thread starvation
- Multiple concurrent withdrawals with predictable ordering
- Better performance under high contention

### 4. **TimeoutLockDemo.java** - Timeout Handling

Shows timeout-based lock acquisition for responsive systems.

```bash
javac TimeoutLockDemo.java handlers/*.java enums/*.java
java TimeoutLockDemo
```

**Features:**

- Non-blocking lock attempts with `tryLock()`
- Timeout support with `tryLock(long, TimeUnit)`
- Prevents indefinite waiting

### 5. **BenchmarkDemo.java** - Performance Testing

Stress-tests the system with 10 concurrent threads performing 1000 total operations.

```bash
javac BenchmarkDemo.java handlers/*.java enums/*.java
java BenchmarkDemo
```

**Metrics:**

- Throughput measurement
- Per-operation timing
- Contention handling

## How It Works

1. **Request Flow:**
   - Withdrawal request starts at ThousandHandler
   - Each handler acquires lock via ReentrantLock
   - Handler dispenses maximum notes it can
   - Remaining amount passes to next handler
   - Lock is released in finally block

2. **Thread Safety:**
   - Each handler maintains exclusive access to its state
   - Lock is acquired before state modification
   - Lock is always released (guaranteed by finally block)
   - No deadlocks due to single lock per handler

3. **Concurrency Benefits:**
   - Multiple threads can queue for different handlers
   - Fair lock prevents starvation
   - High throughput under contention
   - No busy-waiting or busy-spinning

## ReentrantLock Advantages

### 1. **Non-Blocking Lock**

```java
if (lock.tryLock()) {
    try {
        // execute critical section
    } finally {
        lock.unlock();
    }
} else {
    // handle lock unavailable
}
```

### 2. **Timeout-Based Lock**

```java
if (lock.tryLock(2, TimeUnit.SECONDS)) {
    try {
        // execute critical section
    } finally {
        lock.unlock();
    }
}
```

### 3. **Fair Lock Strategy**

```java
ReentrantLock fairLock = new ReentrantLock(true);  // fair = true
```

### 4. **Interruptible Lock**

```java
lock.lockInterruptibly();  // Can be interrupted
```

## Pattern Elements

- **Handler Chain:** ThousandHandler → FiveHundredHandler → TwoHundredHandler → HundredHandler
- **Request:** Withdrawal amount (int)
- **Processing:** Greedy algorithm - dispense maximum notes per denomination
- **Termination:** When remaining amount becomes 0 or no next handler

## Real-World Applications

1. **ATM Systems** - Multiple users withdrawing concurrently
2. **Queue Processing** - Fair task distribution
3. **Resource Allocation** - Non-blocking access patterns
4. **Request Handlers** - Timeout-sensitive operations
5. **Middleware Processing** - Interruptible request handling

## Compilation & Execution

```bash
# Compile all classes
javac COR.java ThreadSafeDemo.java AdvancedReentrantLockDemo.java \
       TimeoutLockDemo.java BenchmarkDemo.java handlers/*.java enums/*.java

# Run different demos
java COR
java ThreadSafeDemo
java AdvancedReentrantLockDemo
java TimeoutLockDemo
java BenchmarkDemo
```

## Performance Notes

- ReentrantLock is faster under high contention (20+ threads)
- Synchronized is simpler but less flexible
- Fair locks have slight overhead but prevent starvation
- try-lock operations are non-blocking

## Learning Outcomes

✓ Chain of Responsibility pattern implementation  
✓ ReentrantLock vs Synchronized comparison  
✓ Fair lock strategies  
✓ Timeout-based locking  
✓ Concurrent programming best practices  
✓ Thread-safe state management  
✓ Lock ordering and deadlock prevention  
