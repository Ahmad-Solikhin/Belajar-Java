package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    void test() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(10);
        final ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1_000);
                    System.out.println("Finish");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}
