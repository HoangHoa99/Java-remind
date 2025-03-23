# Thread

## 1. Definition
- **Independent execution thread** in a Java program.
- Each Java program executes at least one **main thread**.
- You can create additional threads to perform **concurrent tasks**.

## 2. Initialize
- **Extends `Thread` class**:
    ```java
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("Thread is running");
            }
        }
    
        MyThread thread = new MyThread();
        thread.start();
    ```
- **Implements `Runnable` interface (can be managed by `ExecutorService`):**
    ```java
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                    System.out.println("Thread is running");
                }
            }
        
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    ```

3. Thread Life-Cycle

- New: Thread is created but not started.
- Runnable: Thread is started and ready to run. 
- Running: Thread is executing its task.
- Blocked/Waiting: Thread is waiting for a resource or condition to continue.
- Terminated: Thread has completed its task or was interrupted.

4. Synchronization
- Ensures that threads do not access shared resources simultaneously to avoid race conditions.
- How to resolve:
+ Use synchronized methods:
    ```java
        public synchronized void increment() {
            counter++;
        }
    ```
+ Use synchronized blocks:
    ```java
        synchronized (this) {
            counter++;
        }
    ```
+ Use ReentrantLock:
    ```java
        Lock lock = new ReentrantLock();
    
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    ```

5. Thread Pool
   A group of threads that are pre-initialized and managed by ExecutorService.

Types:

FixedThreadPool: Fixed number of threads.

CachedThreadPool: Creates new threads as needed and reuses old ones.

SingleThreadExecutor: Only one thread.

6. Future and Callable
- Callable: Similar to Runnable, but can return a value and throw exceptions.
- Future: Represents the result of an asynchronous computation.

7. Frequently Encountered Problems
- Race Condition: Occurs when multiple threads access and modify shared data simultaneously.
- Deadlock: Occurs when two or more threads are waiting for each other to release resources.
- Starvation: Occurs when a thread cannot access a resource because other threads are holding it.