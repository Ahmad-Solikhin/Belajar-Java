package thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void tetFuture() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
           Thread.sleep(5_000);
           return "hi";
        };

        Future<String> future = service.submit(callable);

        System.out.println("Selesai Future");

        //Menunggu future
        while (!future.isDone()){
            System.out.println("Waitting Future");
            Thread.sleep(1_000);
        }

        //mengambil data future
        String value = future.get();
        System.out.println(value);
    }

    @Test
    void tetFutureCancel() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
           Thread.sleep(5_000);
           return "hi";
        };

        Future<String> future = service.submit(callable);

        System.out.println("Selesai Future");

        //Mmebatalkan future
        Thread.sleep(1_000);
        future.cancel(true);

        //Cek kalo beneran udah di cancel
        System.out.println(future.isCancelled());

        //mengambil data future
        String value = future.get();
        System.out.println(value);
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(v -> (Callable<String>) () -> {
            Thread.sleep(v * 500L);
            return String.valueOf(v);
        }).toList();

        List<Future<String>> futures = executorService.invokeAll(callables);

        for (var stringFuture : futures){
            System.out.println(stringFuture.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(v -> (Callable<String>) () -> {
            Thread.sleep(v * 500L);
            return String.valueOf(v);
        }).toList();

        String futures = executorService.invokeAny(callables);

        System.out.println(futures);
    }
}
