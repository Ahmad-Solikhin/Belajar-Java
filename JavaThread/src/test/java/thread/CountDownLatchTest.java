package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    void test() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Start task");
                    Thread.sleep(2_000);
                    System.out.println("Finish task");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("Finish All Task");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}
