package thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {

    private Long value = 0L;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment(){
        try {
            lock.writeLock().lock();
            this.value++;
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Long getValue(){
        try {
            lock.readLock().lock();
            return this.value;
        }finally {
            lock.readLock().unlock();
        }
    }

}
