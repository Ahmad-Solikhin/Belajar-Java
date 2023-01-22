package thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

    @Test
    void arrayBlockingQueue() throws InterruptedException {
        final ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    array.put("Data");
                    System.out.println("Data Masuk");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    String value = array.take();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void linkedBlockingQueue() throws InterruptedException {
        final LinkedBlockingQueue<String> array = new LinkedBlockingQueue<>();
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    array.put("Data");
                    System.out.println("Data Masuk");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    String value = array.take();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void priorityBlockingQueue() throws InterruptedException {
        final PriorityBlockingQueue<Integer> array = new PriorityBlockingQueue<>(5, Comparator.reverseOrder());
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            final Integer index = i;
            executor.execute(() -> {
                array.put(index);
                System.out.println("Data kw- " + index + " Masuk");

            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    Integer value = array.take();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void delayQueue() throws InterruptedException {
        final DelayQueue<ScheduledFuture<String>> queue = new DelayQueue<>();
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final int index = i;
            queue.put(executor.schedule(() -> "Data ke-" + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true) {
                try {
                    ScheduledFuture<String> value = queue.take();
                    System.out.println("Receive Data : " + value.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void synchronousQueue() throws InterruptedException {
        final SynchronousQueue<String> queue = new SynchronousQueue<>();
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 10; i++) {
            final int index = i;
            executor.execute(() -> {
                try{
                    queue.put("Data ke-" + index);
                    System.out.println("Selesai Memasukkan data ke-" + index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    String value = queue.take();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void blockingDequeue() throws InterruptedException {
        final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i < 10; i++) {
            final int index = i;
            executor.execute(() -> {
                try{
                    queue.putLast("Data ke-" + index);
                    System.out.println("Selesai Memasukkan data ke-" + index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    String value = queue.takeFirst();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void transferQueue() throws InterruptedException {
        final TransferQueue<String> queue = new LinkedTransferQueue<>();
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 10; i++) {
            final int index = i;
            executor.execute(() -> {
                try{
                    queue.transfer("Data ke-" + index);
                    System.out.println("Selesai Memasukkan data ke-" + index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                    String value = queue.take();
                    System.out.println("Receive Data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
    }


}
