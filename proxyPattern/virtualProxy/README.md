# Virtual Proxy Pattern

## Overview
The **Virtual Proxy Pattern** is a structural design pattern that delays the creation of an expensive object until it's actually needed (lazy loading). The proxy acts as a placeholder for the real object and loads it only when required.

## Why Virtual Proxy?
- **Performance**: Avoids loading heavy objects until they're actually used
- **Memory Efficiency**: Objects are created on-demand, not upfront
- **Transparent**: Client code doesn't need to know about lazy loading logic
- **Real-world Example**: Image galleries loading images only when viewed

---

## Architecture

### UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                          <<interface>>                              │
│                            IImage                                   │
├─────────────────────────────────────────────────────────────────────┤
│                                                                      │
│  + display() : void                                                 │
│                                                                      │
└──────────────────────────┬──────────────────────────────────────────┘
                           △
                           │
                           │ implements
                ┌──────────┴──────────┐
                │                     │
    ┌───────────────────────┐  ┌──────────────────────┐
    │     RealImage         │  │    ImageProxy        │
    ├───────────────────────┤  ├──────────────────────┤
    │ - filename: String    │  │ - filename: String   │
    ├───────────────────────┤  │ - realImage: Real... │
    │ + RealImage(file)     │  ├──────────────────────┤
    │ + display(): void     │  │ + ImageProxy(file)   │
    │                       │  │ + display(): void    │
    └───────────────────────┘  └──────────────────────┘
                                         │
                                         │ uses
                                         ▼
                               ┌──────────────────┐
                               │  RealImage       │
                               │ (lazy loaded)    │
                               └──────────────────┘
```

### Component Details

| Component | Role | Responsibility |
|-----------|------|-----------------|
| **IImage** | Subject | Defines the common interface for both RealImage and ImageProxy |
| **RealImage** | Real Subject | The actual heavy object that loads images from disk |
| **ImageProxy** | Proxy | Controls access to RealImage and delays its creation until needed |
| **Client** | Client | Works with objects through the IImage interface |

---

## Sequence Diagram

### Sequence 1: Creating Proxy Objects (No Loading Yet)

```
Client          ImageProxy        RealImage
  │                 │                 │
  │─ new ImageProxy("photo1.jpg")─→  │
  │ (filename stored, realImage=null) │
  │◄─────────────── return ───────────┤
  │                 │                 │
  │─ new ImageProxy("photo2.jpg")─→  │
  │ (filename stored, realImage=null) │
  │◄─────────────── return ───────────┤
  │                 │                 │
```

### Sequence 2: First display() Call (Loads Image)

```
Client          ImageProxy        RealImage
  │                 │                 │
  ├─ display() ────→│                 │
  │                 │                 │
  │                 ├─ check if realImage is null
  │                 │ (YES - needs loading)
  │                 │                 │
  │                 ├─ new RealImage("photo1.jpg")─→
  │                 │                 │
  │                 │        [Loading image from disk...]
  │                 │◄────── return ──┤
  │                 │ (realImage now holds the object)
  │                 │                 │
  │                 ├─ realImage.display()─→
  │                 │                 │
  │                 │        [Displaying photo1.jpg]
  │                 │◄────── return ──┤
  │◄─ return ───────┤                 │
  │                 │                 │
```

### Sequence 3: Subsequent display() Calls (No Reload)

```
Client          ImageProxy        RealImage
  │                 │                 │
  ├─ display() ────→│                 │
  │                 │                 │
  │                 ├─ check if realImage is null
  │                 │ (NO - already loaded)
  │                 │                 │
  │                 ├─ realImage.display()─→
  │                 │                 │
  │                 │    [Displaying photo1.jpg] (cached)
  │                 │◄────── return ──┤
  │◄─ return ───────┤                 │
  │                 │                 │
```

---

## Code Flow Example

```
Step 1: Client creates proxy objects
        IImage image1 = new ImageProxy("photo1.jpg");
        IImage image2 = new ImageProxy("photo2.jpg");
        
        ⏱️  At this point: No loading has occurred
        💾 Memory Usage: Minimal (just filename stored)

Step 2: First display call on image1
        image1.display();
        
        ✅ ImageProxy checks: realImage == null? YES
        📦 Creates: new RealImage("photo1.jpg")
        ⚙️  RealImage constructor loads from disk
        🖼️  Displays image1
        
        ⏱️  Now: Image is fully loaded and cached

Step 3: Subsequent display calls on image1
        image1.display();
        
        ✅ ImageProxy checks: realImage == null? NO
        🚀 Skips object creation
        🖼️  Directly calls realImage.display()
        
        ⏱️  Much faster: No disk I/O needed
```

---

## Key Benefits

✅ **Lazy Loading**
   - Heavy objects created only when needed
   - Reduces startup time

✅ **Caching**
   - Once loaded, object is reused
   - Eliminates redundant loading

✅ **Transparent Access**
   - Client code uses same IImage interface
   - Doesn't know about proxy complexity

✅ **Resource Efficiency**
   - Memory allocated on-demand
   - Perfect for large collections of expensive objects

---

## Use Cases

1. **Image Galleries** - Load images only when viewed
2. **Document Editors** - Load large documents on first access
3. **Database Connections** - Create connections when needed
4. **Lazy Collections** - Load collection items on iteration
5. **Remote Objects** - Load remote resources on-demand

---

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|------------|
| **Virtual Proxy** | Lazy loading | Heavy objects, on-demand creation |
| **Protection Proxy** | Access control | Restrict access to sensitive objects |
| **Remote Proxy** | Remote objects | Objects on different machines |
| **Decorator** | Add behavior | Enhancement without proxy semantics |
| **Adapter** | Interface change | Making incompatible interfaces work |

---

## Implementation Summary

### The Magic Happens Here

```java
@Override
public void display() {
    if (realImage == null) {           // Check if loaded
        realImage = new RealImage(filename);  // Lazy load
    }
    realImage.display();               // Delegate to real object
}
```

This simple check implements the entire virtual proxy pattern:
1. **First call**: realImage is null → Create it (lazy load)
2. **Later calls**: realImage exists → Use cached instance
3. **Client**: Doesn't care about the difference

---

## Execution Output

```
Client creates two proxies (fast, no loading):
  image1 = new ImageProxy("photo1.jpg");
  image2 = new ImageProxy("photo2.jpg");

First display() on image1:
  image1.display();
  → [RealImage] Loading image from disk: photo1.jpg
  → [RealImage] Displaying photo1.jpg

First display() on image2:
  image2.display();
  → [RealImage] Loading image from disk: photo2.jpg
  → [RealImage] Displaying photo2.jpg

Second display() on image1 (no reload):
  image1.display();
  → [RealImage] Displaying photo1.jpg   ← No "Loading" message!
```

---

## Conclusion

The Virtual Proxy Pattern is essential for scenarios where object creation is expensive. By deferring creation until the object is actually needed, we gain significant performance improvements and resource efficiency while maintaining a clean, transparent interface for clients.
