package chain_of_responsibility.handlers;

import java.util.concurrent.locks.ReentrantLock;

abstract class IMoneyHandler {

    protected volatile IMoneyHandler nextHandler;
    protected final ReentrantLock lock = new ReentrantLock();

    public IMoneyHandler() {
        this.nextHandler = null;
    }

    public void setNextHandler(IMoneyHandler nexHandler) {
        lock.lock();
        try {
            this.nextHandler = nexHandler;
        } finally {
            lock.unlock();
        }
    }

    public IMoneyHandler getNextHandler() {
        lock.lock();
        try {
            return nextHandler;
        } finally {
            lock.unlock();
        }
    }

    public abstract void dispense(int amount);
} 