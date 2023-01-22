package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamtest {

    @Test
    void paralel(){
        Stream<Integer> stream = IntStream.range(0, 1_000).boxed();

        stream.parallel().forEach(v -> {
            System.out.println(Thread.currentThread().getName() + " . " + v);
        });
    }

    @Test
    void customPool(){
        ForkJoinPool pool = new ForkJoinPool(5);

        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 1_000).boxed();
            stream.parallel().forEach(v -> {
                System.out.println(Thread.currentThread().getName() + " : " + v);
            });
        });

        task.join();

    }
}
