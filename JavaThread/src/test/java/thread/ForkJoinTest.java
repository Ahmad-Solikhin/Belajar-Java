package thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {

    @Test
    void create(){
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(2);

        ExecutorService executor1 = Executors.newWorkStealingPool();
        ExecutorService executor2 = Executors.newWorkStealingPool(2);
    }

    @Test
    void recursiveAction() throws InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

        SimpleForkJoinTask task = new SimpleForkJoinTask(integers);

        forkJoinPool.execute(task);

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    void recursiveTask() throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        TotalRecursiveTask task = new TotalRecursiveTask(integers);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);

        Long total = submit.get();
        System.out.println(total);

        System.out.println();
        Long sum = integers.stream().mapToLong(v-> v).sum();
        System.out.println(sum);

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    }

    public static class SimpleForkJoinTask extends RecursiveAction {

        private List<Integer> integers;

        public SimpleForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            if (integers.size() < 10){
                //do job
                doExecute();
            } else {
                //fork
                forkCompute();
            }
        }

        private void forkCompute() {
            //membagi 2 list
            List<Integer> integer1 = this.integers.subList(0, this.integers.size() / 2);
            List<Integer> integer2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

            //Buat simplefrokjointasknya
            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integer1);
            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integer2);

            ForkJoinTask.invokeAll(task1, task2);

        }

        private void doExecute() {
            integers.forEach(v -> System.out.println(
                    Thread.currentThread().getName() + "." + v
            ));
        }
    }

    public static class TotalRecursiveTask extends RecursiveTask<Long>{

        private List<Integer> integers;

        public TotalRecursiveTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() < 10){
                return doCompute();
            }else {
                return forkCompute();
            }
        }

        private Long forkCompute() {
            //membagi 2 list
            List<Integer> integer1 = this.integers.subList(0, this.integers.size() / 2);
            List<Integer> integer2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

            TotalRecursiveTask task1 = new TotalRecursiveTask(integer1);
            TotalRecursiveTask task2 = new TotalRecursiveTask(integer2);

            ForkJoinTask.invokeAll(task1, task2);

            return task1.join() + task2.join();
        }

        private Long doCompute() {
            return integers.stream().mapToLong(v -> v).peek(value -> {
                System.out.println(Thread.currentThread().getName() + " . " + value);
            }).sum();
        }
    }
}
