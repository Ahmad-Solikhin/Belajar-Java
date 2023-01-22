package thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void createThread(){
        Runnable runnable = () -> {
            System.out.println("Hello thread : " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesai");
    }

    @Test
    void threadSleep() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesai");

        Thread.sleep(3_000);
    }

    @Test
    void threadJoin() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Menungu Selesai");
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void threadInterrupt() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++){
                System.out.println("Runnabel ke-" + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    //System.out.println(e.getMessage());
                    //Kalo kedeteksi interrupt harusnya dihentikan
                    return;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menungu Selesai");
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void threadInterruptRealImplementation() throws  InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++){
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnabel ke-" + i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menungu Selesai");
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void threadName(){
        Thread thread = new Thread(() -> {
            System.out.println("Jalan di Thread : " + Thread.currentThread().getName());
        });

        thread.setName("Gayuh");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Jalan di Thread : " + Thread.currentThread().getName());
        });

        thread.setName("Gayuh");
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }
}
