package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    @Test
    void singleThread() throws InterruptedException{
        ExecutorService executors = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executors.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Run : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executors.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void fixThread() throws InterruptedException{
        ExecutorService executors = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executors.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    System.out.println("Run : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}
