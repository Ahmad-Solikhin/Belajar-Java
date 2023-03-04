package com.gayuh.resilience4j;

import io.github.resilience4j.bulkhead.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

@Slf4j
public class BulkheadTest {

    private AtomicLong counter = new AtomicLong(0L);

    @SneakyThrows
    public void slow(){
        Long value = counter.incrementAndGet();
        log.info("slow : {}", value);
        Thread.sleep(1_000L);
    }

    @Test
    void testSemaphore() throws InterruptedException {
        Bulkhead bulkhead = Bulkhead.ofDefaults("gayuh");

        for (int i = 0; i < 1_000; i++) {
            Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
            new Thread(runnable).start();
        }

        Thread.sleep(10_000);
    }

    @Test
    void testThreadPool(){

//        log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
        ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults("gayuh");

        for (int i = 0; i < 1_000; i++) {
            Supplier<CompletionStage<Void>> supplier = ThreadPoolBulkhead.decorateRunnable(threadPoolBulkhead, this::slow);
            supplier.get();
        }
    }

    @Test
    void testSemaphoreConfig() throws InterruptedException {

        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(5)
                .maxWaitDuration(Duration.ofSeconds(5))
                .build();

        Bulkhead bulkhead = Bulkhead.of("gayuh", config);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
            new Thread(runnable).start();
        }

        Thread.sleep(10_000);
    }

    @Test
    void testThreadPoolConfig() throws InterruptedException {

        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(5)
                .coreThreadPoolSize(5)
                .build();

//        log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
        ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.of("gayuh", config);

        for (int i = 0; i < 20; i++) {
            Supplier<CompletionStage<Void>> supplier = ThreadPoolBulkhead.decorateRunnable(threadPoolBulkhead, this::slow);
            supplier.get();
        }

        Thread.sleep(10_000);
    }

    @Test
    void bulkheadSemaphoreRegistry() throws InterruptedException {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(5)
                .maxWaitDuration(Duration.ofSeconds(5))
                .build();

        BulkheadRegistry registry = BulkheadRegistry.ofDefaults();
        registry.addConfiguration("config", config);

        Bulkhead bulkhead = registry.bulkhead("gayuh", "config");

        for (int i = 0; i < 10; i++) {
            Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
            new Thread(runnable).start();
        }

        Thread.sleep(10_000);
    }

    @Test
    void testThreadPoolRegistry() throws InterruptedException {

        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(5)
                .coreThreadPoolSize(5)
                .build();

        ThreadPoolBulkheadRegistry registry = ThreadPoolBulkheadRegistry.ofDefaults();
        registry.addConfiguration("config", config);

//        log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
        ThreadPoolBulkhead threadPoolBulkhead = registry.bulkhead("gayuh", "config");

        for (int i = 0; i < 20; i++) {
            Supplier<CompletionStage<Void>> supplier = ThreadPoolBulkhead.decorateRunnable(threadPoolBulkhead, this::slow);
            supplier.get();
        }

        Thread.sleep(10_000);
    }

}
