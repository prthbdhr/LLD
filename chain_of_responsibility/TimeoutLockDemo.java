import chain_of_responsibility.handlers.*;
import java.util.concurrent.TimeUnit;

/**
 * Advanced Demo with tryLock for timeout handling
 * Shows how to handle withdrawal timeouts and non-blocking lock acquisition
 */
public class TimeoutLockDemo {
    public static void main(String[] args) {
        System.out.println("=== Timeout-Based Lock Demo ===\n");

        IMoneyHandler thousandHandler = new ThousandHandler(10);
        IMoneyHandler fiveHundredHandler = new FiveHundredHandler(15);
        IMoneyHandler twoHundredHandler = new TwoHundredHandler(20);
        IMoneyHandler hundredHandler = new HundredHandler(50);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        // Simulate a long-running transaction on one handler
        Thread longTransaction = new Thread(() -> {
            System.out.println("[Long-Running] Starting 3-second transaction...");
            try {
                thousandHandler.dispense(2500);
                Thread.sleep(3000); // Simulate processing
                System.out.println("[Long-Running] Transaction completed.\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Long-Running");

        // Try to access with timeout
        Thread timeoutUser = new Thread(() -> {
            try {
                Thread.sleep(500); // Wait for long transaction to start
                System.out.println("[Timeout-User] Attempting withdrawal with 2-second timeout...");
                
                long startWait = System.currentTimeMillis();
                System.out.println("[Timeout-User] Requesting withdrawal...");
                thousandHandler.dispense(1500);
                long waitTime = System.currentTimeMillis() - startWait;
                
                System.out.println("[Timeout-User] Withdrawal completed in " + waitTime + "ms\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Timeout-User");

        longTransaction.start();
        timeoutUser.start();

        try {
            longTransaction.join();
            timeoutUser.join();
            
            System.out.println("=== Demo Complete ===");
            System.out.println("\nReentrantLock provides:");
            System.out.println("✓ Timeout-aware locking with tryLock(long, TimeUnit)");
            System.out.println("✓ Non-blocking lock attempts");
            System.out.println("✓ Interruptible lock acquisition");
            System.out.println("✓ Better throughput in high-contention scenarios");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}