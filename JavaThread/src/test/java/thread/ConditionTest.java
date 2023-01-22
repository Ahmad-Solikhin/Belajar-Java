package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    String message;
    @Test
    void condition() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                System.out.println(message);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
           try {
               lock.lock();
               Thread.sleep(2_000);
               message = "Gayuh";
               condition.signal();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } finally {
               lock.unlock();
           }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
