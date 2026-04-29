import chain_of_responsibility.handlers.*;
import java.util.concurrent.TimeUnit;

/**
 * Advanced ReentrantLock Demo with Fair Lock Strategy
 * Demonstrates advanced ReentrantLock features for efficient thread-safe ATM operations
 */
public class AdvancedReentrantLockDemo {
    public static void main(String[] args) {
        System.out.println("=== Advanced ReentrantLock Demo ===\n");

        // Create handlers with fair lock strategy for FIFO ordering
        IMoneyHandler thousandHandler = new ThousandHandler(20);
        IMoneyHandler fiveHundredHandler = new FiveHundredHandler(30);
        IMoneyHandler twoHundredHandler = new TwoHundredHandler(40);
        IMoneyHandler hundredHandler = new HundredHandler(100);

        // Chain them
        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        System.out.println("Testing tryLock with timeout feature:\n");

        // Thread 1: Normal withdrawal with timeout
        Thread user1 = new Thread(() -> {
            System.out.println("[User-1] Attempting withdrawal of ₹3500 with timeout...");
            thousandHandler.dispense(3500);
            System.out.println("[User-1] Withdrawal completed.\n");
        }, "User-1");

        // Thread 2: Concurrent withdrawal
        Thread user2 = new Thread(() -> {
            try {
                Thread.sleep(100); // Slight delay to allow user1 to start
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[User-2] Attempting withdrawal of ₹2800...");
            thousandHandler.dispense(2800);
            System.out.println("[User-2] Withdrawal completed.\n");
        }, "User-2");

        // Thread 3: Another concurrent withdrawal
        Thread user3 = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[User-3] Attempting withdrawal of ₹1500...");
            thousandHandler.dispense(1500);
            System.out.println("[User-3] Withdrawal completed.\n");
        }, "User-3");

        // Thread 4: High-priority withdrawal with tryLock
        Thread user4 = new Thread(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[User-4] Attempting withdrawal of ₹900 (with timeout strategy)...");
            thousandHandler.dispense(900);
            System.out.println("[User-4] Withdrawal completed.\n");
        }, "User-4");

        // Start all threads
        long startTime = System.currentTimeMillis();
        user1.start();
        user2.start();
        user3.start();
        user4.start();

        // Wait for completion
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
            long endTime = System.currentTimeMillis();
            
            System.out.println("=== All withdrawals processed safely ===");
            System.out.println("Total time: " + (endTime - startTime) + "ms");
            System.out.println("\nReentrantLock Benefits:");
            System.out.println("✓ Better performance than synchronized");
            System.out.println("✓ Fair lock ordering (FIFO)");
            System.out.println("✓ tryLock() for timeout handling");
            System.out.println("✓ More granular control over locking");
            System.out.println("✓ Better for high-contention scenarios");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}