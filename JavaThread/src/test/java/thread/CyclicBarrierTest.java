package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    @Test
    void test() throws InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Waiting");
                    cyclicBarrier.await();
                    System.out.println("DoneWaiting");
                }catch (InterruptedException | BrokenBarrierException exception){
                    exception.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
