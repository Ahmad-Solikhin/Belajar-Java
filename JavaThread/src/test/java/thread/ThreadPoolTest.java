package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    @Test
    void create(){
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread,alive, aliveTime, queue);

    }

    @Test
    void execute(){
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread,alive, aliveTime, queue);

        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Runnable for thread : " + Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shutdown(){
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread,alive, aliveTime, queue);

        for (int i = 0; i < 100; i++) {
            final int task = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " Runnable for thread : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        try {
            threadPoolExecutor.shutdownNow();
            threadPoolExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void rejected(){
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

        RejectedExecutionHandler rejectedExecutionHandler = new LogRejected();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread,alive, aliveTime, queue, rejectedExecutionHandler);

        for (int i = 0; i < 1000; i++) {
            final int task = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " Runnable for thread : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        try {
//            threadPoolExecutor.shutdownNow();
            threadPoolExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class LogRejected implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task " + r + "is rejected");
        }
    }
}
