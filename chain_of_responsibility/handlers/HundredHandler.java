package chain_of_responsibility.handlers;

import java.util.concurrent.locks.ReentrantLock;

public class HundredHandler extends IMoneyHandler {
    private volatile int numNotes;
    private final ReentrantLock dispenseLock = new ReentrantLock();

    public HundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        dispenseLock.lock();
        try {
            int notesNeeded = amount / 100;

            if (notesNeeded > numNotes) {
                notesNeeded = numNotes;
                numNotes = 0;
            } else {
                numNotes -= notesNeeded;
            }

            if (notesNeeded > 0)
                System.out.println("Dispensing " + notesNeeded + " x ₹100 notes.");

            int remainingAmount = amount - (notesNeeded * 100);
            if (remainingAmount > 0) {
                IMoneyHandler next = getNextHandler();
                if (next != null) {
                    next.dispense(remainingAmount);
                } else {
                    System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficient fund in ATM)");
                }
            }
        } finally {
            dispenseLock.unlock();
        }
    }

    public int getAvailableNotes() {
        dispenseLock.lock();
        try {
            return numNotes;
        } finally {
            dispenseLock.unlock();
        }
    }
}