package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void test() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                System.out.println("Thread 1 send data : first");
                Thread.sleep(1_000);
                String result = exchanger.exchange("First");
                System.out.println("Thread 1 Receive data : " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.execute(() -> {
            try {
                System.out.println("Thread 2 send data : second");
                Thread.sleep(2_000);
                String result = exchanger.exchange("Second");
                System.out.println("Thread 2 Receive data : " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
