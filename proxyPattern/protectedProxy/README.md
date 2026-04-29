# Protection Proxy Pattern

## Overview

The **Protection Proxy Pattern** is a structural design pattern that controls access to an object by acting as a gatekeeper. It provides additional security by checking permissions before allowing operations on the real object.

## Why Protection Proxy?

- **Security**: Enforces access control and authorization
- **Encapsulation**: Hides sensitive operations behind a secure interface
- **Flexibility**: Easy to modify access rules without changing the real object
- **Real-world Example**: Bank account access requiring PIN verification

---

## Architecture

### UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                          <<interface>>                              │
│                     ISensitiveDataService                           │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  + accessData(userRole: String) : void                              │
│                                                                     │
└──────────────────────────┬──────────────────────────────────────────┘
                           △
                           │
                           │ implements
                ┌──────────┴──────────┐
                │                     │
    ┌───────────────────────┐  ┌──────────────────────┐
    │ RealSensitiveData...  │  │ SensitiveDataSer...  │
    ├───────────────────────┤  ├──────────────────────┤
    │                       │  │ - realService: Re... │
    │                       │  │ - allowedRoles: S... │
    ├───────────────────────┤  ├──────────────────────┤
    │ + accessData(role)    │  │ + SensitiveDataS...  │
    │                       │  │ + accessData(role)   │
    └───────────────────────┘  └──────────────────────┘
                                         │
                                         │ controls access to
                                         ▼
                               ┌──────────────────┐
                               │ RealSensitive... │
                               │ (protected)      │
                               └──────────────────┘
```

### Component Details

| Component | Role | Responsibility |
|-----------|------|-----------------|
| **ISensitiveDataService** | Subject | Defines the common interface for both real service and proxy |
| **RealSensitiveDataService** | Real Subject | The actual service that handles sensitive data operations |
| **SensitiveDataServiceProxy** | Proxy | Controls access to RealSensitiveDataService by checking user roles |
| **Client** | Client | Works with objects through the ISensitiveDataService interface |

---

## Sequence Diagram

### Sequence 1: Authorized Access

```
Client          SensitiveDataServiceProxy        RealSensitiveDataService
  │                 │                 │
  ├─ accessData("ADMIN") ────→│                 │
  │                 │                 │
  │                 ├─ check if "ADMIN" is authorized
  │                 │ (YES - role allowed)
  │                 │                 │
  │                 ├─ [Access granted for role: ADMIN]
  │                 │                 │
  │                 ├─ realService.accessData("ADMIN")─→
  │                 │                 │
  │                 │        [Accessing sensitive data...]
  │                 │◄────── return ──┤
  │◄─ return ───────┤                 │
  │                 │                 │
```

### Sequence 2: Unauthorized Access

```
Client          SensitiveDataServiceProxy        RealSensitiveDataService
  │                 │                 │
  ├─ accessData("USER") ────→│                 │
  │                 │                 │
  │                 ├─ check if "USER" is authorized
  │                 │ (NO - role not allowed)
  │                 │                 │
  │                 ├─ [Access denied for role: USER]
  │                 │                 │
  │                 ├─ throw SecurityException
  │                 │                 │
  │◄─ SecurityException ───┤                 │
  │                 │                 │
```

---

## Code Flow Example

```
Step 1: Client creates proxy
        ISensitiveDataService proxy = new SensitiveDataServiceProxy();
        
        🔐 Proxy initialized with allowed roles: ["ADMIN", "MANAGER"]

Step 2: Authorized access attempt
        proxy.accessData("ADMIN");
        
        ✅ Proxy checks: is "ADMIN" in allowedRoles? YES
        🔓 Access granted
        📊 Calls realService.accessData("ADMIN")
        📄 Returns sensitive data

Step 3: Unauthorized access attempt
        proxy.accessData("USER");
        
        ❌ Proxy checks: is "USER" in allowedRoles? NO
        🚫 Access denied
        ⚠️  Throws SecurityException
```

---

## Key Benefits

✅ **Access Control**

- Enforces role-based permissions
- Prevents unauthorized operations

✅ **Security Layer**

- Additional security without modifying real object
- Centralized authorization logic

✅ **Transparent Interface**

- Client code uses same interface
- Security checks are invisible to clients

✅ **Flexible Authorization**

- Easy to modify access rules
- Support for different user roles

---

## Use Cases

1. **Banking Systems** - Account access requiring authentication
2. **File Systems** - File access based on user permissions
3. **Database Access** - Query restrictions based on user roles
4. **API Gateways** - Service access control and rate limiting
5. **Medical Records** - Patient data access for authorized personnel only

---

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|------------|
| **Protection Proxy** | Access control | Restrict access to sensitive objects |
| **Virtual Proxy** | Lazy loading | Heavy objects, on-demand creation |
| **Remote Proxy** | Remote objects | Objects on different machines |
| **Decorator** | Add behavior | Enhancement without proxy semantics |
| **Adapter** | Interface change | Making incompatible interfaces work |
