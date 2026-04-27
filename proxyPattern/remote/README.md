# Remote Proxy Pattern

## Overview

The **Remote Proxy Pattern** is a structural design pattern that provides a local representative for an object in a different address space. It handles the complexities of network communication and makes remote objects appear as if they are local.

## Why Remote Proxy?

- **Location Transparency**: Remote objects appear local to the client
- **Network Abstraction**: Hides network communication details
- **Lazy Connection**: Establishes connections only when needed
- **Real-world Example**: Distributed systems accessing remote services

---

## Architecture

### UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                          <<interface>>                              │
│                           IDataService                              │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  + fetchData() : Data                                               │
│                                                                     │
└──────────────────────────┬──────────────────────────────────────────┘
                           △
                           │
                           │ implements
                ┌──────────┴──────────┐
                │                     │
    ┌───────────────────────┐  ┌──────────────────────┐
    │   RealDataService     │  │  DataServiceProxy    │
    ├───────────────────────┤  ├──────────────────────┤
    │                       │  │ - realDataService... │
    ├───────────────────────┤  ├──────────────────────┤
    │ + fetchData(): Data   │  │ + DataServiceProxy() │
    │                       │  │ + fetchData(): Data  │
    └───────────────────────┘  └──────────────────────┘
                                         │
                                         │ manages connection to
                                         ▼
                               ┌──────────────────┐
                               │ RealDataService  │
                               │ (remote)         │
                               └──────────────────┘
```

### Component Details

| Component | Role | Responsibility |
|-----------|------|-----------------|
| **IDataService** | Subject | Defines the common interface for both real service and proxy |
| **RealDataService** | Real Subject | The actual remote service that fetches data |
| **DataServiceProxy** | Proxy | Manages connection to RealDataService and handles remote communication |
| **Data** | Data Transfer Object | Represents the data returned from the service |
| **Client** | Client | Works with objects through the IDataService interface |

---

## Sequence Diagram

### Sequence 1: First fetchData() Call (Establishes Connection)

```
Client          DataServiceProxy        RealDataService
  │                 │                 │
  ├─ fetchData() ────→│                 │
  │                 │                 │
  │                 ├─ check if realDataService is null
  │                 │ (YES - needs connection)
  │                 │                 │
  │                 ├─ [Initializing RealDataService...]
  │                 │                 │
  │                 ├─ new RealDataService()─→
  │                 │                 │
  │                 │        [Initialized (simulating remote setup)]
  │                 │◄────── return ──┤
  │                 │ (connection established)
  │                 │                 │
  │                 ├─ realDataService.fetchData()─→
  │                 │                 │
  │                 │        [Returns data from remote service]
  │                 │◄────── Data ────┤
  │◄─ Data ─────────┤                 │
  │                 │                 │
```

### Sequence 2: Subsequent fetchData() Calls (Reuses Connection)

```
Client          DataServiceProxy        RealDataService
  │                 │                 │
  ├─ fetchData() ────→│                 │
  │                 │                 │
  │                 ├─ check if realDataService is null
  │                 │ (NO - connection exists)
  │                 │                 │
  │                 ├─ [Using cached RealDataService instance.]
  │                 │                 │
  │                 ├─ realDataService.fetchData()─→
  │                 │                 │
  │                 │    [Returns data from remote service]
  │                 │◄────── Data ────┤
  │◄─ Data ─────────┤                 │
  │                 │                 │
```

---

## Code Flow Example

```
Step 1: Client creates proxy
        IDataService dataService = new DataServiceProxy();
        
        🌐 Proxy initialized, realDataService = null
        📡 No remote connection established yet

Step 2: First fetchData call
        dataService.fetchData();
        
        ✅ Proxy checks: realDataService == null? YES
        🔗 Creates: new RealDataService() (establishes connection)
        ⏳ Simulates network latency (5 seconds)
        📦 Fetches data from remote service
        💾 Returns data and caches connection

Step 3: Subsequent fetchData calls
        dataService.fetchData();
        
        ✅ Proxy checks: realDataService == null? NO
        🚀 Reuses existing connection
        📦 Directly fetches data (no connection overhead)
        ⚡ Much faster: No network setup needed
```

---

## Key Benefits

✅ **Location Transparency**

- Remote objects appear local
- Client code doesn't handle network details

✅ **Connection Management**

- Lazy connection establishment
- Connection reuse for efficiency

✅ **Network Abstraction**

- Hides network communication complexity
- Handles remote service initialization

✅ **Performance Optimization**

- Avoids unnecessary network calls
- Caches remote service instances

---

## Use Cases

1. **Distributed Systems** - Accessing services across different machines
2. **Microservices** - Communication between microservices
3. **Cloud Services** - Accessing cloud-hosted resources
4. **Database Proxies** - Remote database access through local interface
5. **Web Services** - REST API calls abstracted as local method calls

---

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|------------|
| **Remote Proxy** | Remote objects | Objects on different machines |
| **Virtual Proxy** | Lazy loading | Heavy objects, on-demand creation |
| **Protection Proxy** | Access control | Restrict access to sensitive objects |
| **Decorator** | Add behavior | Enhancement without proxy semantics |
| **Adapter** | Interface change | Making incompatible interfaces work |