package thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void manual() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
           while (this.message == null){
               //wait
           }
            System.out.println(message);
        });

        Thread thread2 = new Thread(() -> {
            this.message = "Gayuh";
        });

        thread1.start();
        thread2.start();

        thread2.join();
        thread1.join();
    }

    @Test
    void automation(){
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
           synchronized (lock){
               try {
                   lock.wait();
                   System.out.println(this.message);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        Thread thread2 = new Thread(() -> {
           synchronized (lock){
               this.message = "Gayuh";
               lock.notify();
           }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
