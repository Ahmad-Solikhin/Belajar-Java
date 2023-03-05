package com.gayuh.resilience4j;

import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
public class DecoratorsTest {

    @SneakyThrows
    public void slow(){
        log.info("Slow");
        Thread.sleep(1_000L);
        throw new IllegalArgumentException("uos");
    }

    @SneakyThrows
    public String sayHello(){
        log.info("Say Helo");
        Thread.sleep(1_000L);
        throw new IllegalArgumentException("ups");
    }

    @Test
    void test() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.of("gayuh", RateLimiterConfig.custom()
                        .limitForPeriod(5)
                        .limitRefreshPeriod(Duration.ofMinutes(1))
                        .build());

        Retry retry = Retry.of("gayuh", RetryConfig.custom()
                        .maxAttempts(10)
                        .waitDuration(Duration.ofMillis(10))
                        .build());

        Runnable runnable = Decorators.ofRunnable(this::slow)
                .withRetry(retry)
                .withRateLimiter(rateLimiter)
                .decorate();

        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(10_000);
    }

    @Test
    void fallback() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.of("gayuh", RateLimiterConfig.custom()
                        .limitForPeriod(5)
                        .limitRefreshPeriod(Duration.ofMinutes(1))
                        .build());

        Retry retry = Retry.of("gayuh", RetryConfig.custom()
                        .maxAttempts(10)
                        .waitDuration(Duration.ofMillis(10))
                        .build());

        Supplier<String> supplier = Decorators.ofSupplier(this::sayHello)
                .withRetry(retry)
                .withRateLimiter(rateLimiter)
                .withFallback(throwable -> "Hello huest")
                .decorate();

        System.out.println(supplier.get());
    }

}
