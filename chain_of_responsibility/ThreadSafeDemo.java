import chain_of_responsibility.handlers.*;

/**
 * Thread-safe demonstration of the Chain of Responsibility pattern
 * Shows how multiple threads can safely access the ATM system concurrently
 */
public class ThreadSafeDemo {
    public static void main(String[] args) {
        // Create handlers with initial note counts
        IMoneyHandler thousandHandler = new ThousandHandler(10);
        IMoneyHandler fiveHundredHandler = new FiveHundredHandler(15);
        IMoneyHandler twoHundredHandler = new TwoHundredHandler(20);
        IMoneyHandler hundredHandler = new HundredHandler(50);

        // Set up chain: 1000 -> 500 -> 200 -> 100
        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        System.out.println("=== Thread-Safe ATM System ===\n");

        // Create multiple threads simulating concurrent ATM users
        Thread user1 = new Thread(() -> {
            System.out.println("[User 1] Requesting withdrawal: ₹2500");
            thousandHandler.dispense(2500);
            System.out.println("[User 1] Withdrawal completed\n");
        }, "User-1");

        Thread user2 = new Thread(() -> {
            System.out.println("[User 2] Requesting withdrawal: ₹1800");
            thousandHandler.dispense(1800);
            System.out.println("[User 2] Withdrawal completed\n");
        }, "User-2");

        Thread user3 = new Thread(() -> {
            System.out.println("[User 3] Requesting withdrawal: ₹3200");
            thousandHandler.dispense(3200);
            System.out.println("[User 3] Withdrawal completed\n");
        }, "User-3");

        Thread user4 = new Thread(() -> {
            System.out.println("[User 4] Requesting withdrawal: ₹1500");
            thousandHandler.dispense(1500);
            System.out.println("[User 4] Withdrawal completed\n");
        }, "User-4");

        // Start all threads simultaneously
        user1.start();
        user2.start();
        user3.start();
        user4.start();

        // Wait for all threads to complete
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
            System.out.println("=== All withdrawals processed safely ===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}