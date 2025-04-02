# Memory Management & Garbage Collection in Java - Summary

## 1. JVM Memory Structure
### Heap Memory
- **Young Generation** (Eden + Survivor Spaces)
    - New object allocation
    - Minor GC (fast, frequent)
- **Old Generation** (Tenured Space)
    - Long-lived objects
    - Major GC (slower)
- **Metaspace** (Java 8+)
    - Class metadata (replaced PermGen)

### Non-Heap Memory
- **Stack**: Thread-specific (methods, locals)
- **Native**: JVM internal use

## 2. Garbage Collection
### Key Concepts
- **Reachability**: Objects unreachable from GC roots are collected
- **STW (Stop-the-World)**: Application pauses during GC

### GC Algorithms
| Algorithm     | Type          | Use Case                      |
|---------------|---------------|-------------------------------|
| Serial        | Single-thread | Small apps                    |
| Parallel      | Throughput    | Batch processing              |
| G1 (Default)  | Balanced      | General purpose (Java 9+)     |
| ZGC/Shenandoah| Low-latency   | Large heaps, real-time systems|

## 3. Memory Leaks
### Common Causes
- Static collections
- Unclosed resources (files/DB connections)
- ThreadLocal misuse

### Detection Tools
- `jvisualvm`, `jconsole`
- Heap dumps (`jmap -dump:format=b,file=heap.hprof <pid>`)

## 4. Best Practices
1. **Minimize object creation** (reuse objects)
2. **Prefer primitives** (`int` over `Integer`)
3. **Close resources** (`try-with-resources`)
4. **Tune GC**: `-XX:+UseG1GC`, `-Xmx4g`
5. **Monitor**: `jstat -gc <pid>`

## 5. Key JVM Flags
```sh
# Heap sizing
-Xmx4g  # Max heap
-Xms1g  # Initial heap

# GC Selection
-XX:+UseG1GC       # G1 (default)
-XX:+UseZGC        # ZGC (Java 15+)

# Monitoring
-XX:+HeapDumpOnOutOfMemoryError
```

## 3. FAQ
**Q: Heap vs Stack?**

A: Heap stores objects (GC-managed); Stack stores method calls (thread-specific).

**Q: How to fix OOM errors?**
1. Analyze heap dump

2. Increase -Xmx

3. Optimize object usage

**Q: What triggers a GC?**

A: Heap exhaustion or explicit System.gc() (avoid in production).