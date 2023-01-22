package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {

    @Test
    void test() throws InterruptedException{
        final ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    int random = ThreadLocalRandom.current().nextInt();
                    System.out.println(random);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    void stream(){
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            ThreadLocalRandom.current().ints(1000, 0, 1000)
                    .forEach(System.out::println);
        });

        executor.shutdown();

    }
}
