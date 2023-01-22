package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ConcurrentMapTest {

    @Test
    void concurrentMap() throws InterruptedException {

        final CountDownLatch count = new CountDownLatch(100);
        final ConcurrentMap<Integer, String> map = new ConcurrentHashMap<>();
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final int index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    map.putIfAbsent(index, "Data ke-" + index);
                    System.out.println("Sukses memasukan Data ke-" + index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    count.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                count.await();
                map.forEach((k, v) -> System.out.println(k + " : " + v));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
