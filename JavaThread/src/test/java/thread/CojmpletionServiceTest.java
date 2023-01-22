package thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CojmpletionServiceTest {

    private Random random = new Random();

    @Test
    void test() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<String> completion = new ExecutorCompletionService<>(executor);

        //Submit task
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final int index = i;
                completion.submit(() -> {
                    Thread.sleep(random.nextInt(2_000));
                    return "Task " + index;
                });
            }
        });

        //Pool task
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true){
                try {
                    Future<String> future = completion.poll(5, TimeUnit.SECONDS);
                    if (future == null){
                        break;
                    } else {
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
