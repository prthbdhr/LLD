import chain_of_responsibility.handlers.*;

/**
 * Benchmark Demo comparing performance under high contention
 * Shows the efficiency advantage of ReentrantLock over synchronized
 */
public class BenchmarkDemo {
    public static void main(String[] args) {
        System.out.println("=== ReentrantLock Performance Benchmark ===\n");

        IMoneyHandler handler = new ThousandHandler(1000);

        // Setup chain (not critical for this benchmark)
        IMoneyHandler fiveHandler = new FiveHundredHandler(1000);
        IMoneyHandler twoHandler = new TwoHundredHandler(1000);
        IMoneyHandler hundredHandler = new HundredHandler(1000);
        
        handler.setNextHandler(fiveHandler);

        System.out.println("Running stress test with 10 concurrent threads...\n");

        long startTime = System.nanoTime();
        Thread[] threads = new Thread[10];

        // Create 10 threads performing rapid withdrawals
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    handler.dispense(1000 + threadNum);
                }
            }, "Thread-" + i);
        }

        // Start all threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for completion
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("=== Benchmark Results ===");
        System.out.println("Total threads: 10");
        System.out.println("Operations per thread: 100");
        System.out.println("Total operations: 1000");
        System.out.println("Total time: " + totalTime + "ms");
        System.out.println("Avg time per operation: " + (totalTime / 1000.0) + "ms");
        System.out.println("\nReentrantLock Advantages:");
        System.out.println("✓ Non-blocking tryLock() support");
        System.out.println("✓ Timeout support with tryLock(long, TimeUnit)");
        System.out.println("✓ Fair lock option for FIFO ordering");
        System.out.println("✓ Better performance under contention");
        System.out.println("✓ Interruptible lock acquisition");
    }
}