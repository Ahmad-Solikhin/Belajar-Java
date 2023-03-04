package com.gayuh.resilience4j;

import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@Slf4j
public class RetryTest {

    void callMe() {
        log.info("Try call me");
        throw new IllegalArgumentException("Ups error");
    }

    @Test
    void testRunnable() {
        Retry retry = Retry.ofDefaults("Gayuh");

        Runnable runnable = Retry.decorateRunnable(retry, this::callMe);

        runnable.run();
    }

    private String hello() {
        log.info("call hello");
        throw new IllegalArgumentException("Ups error again");
    }

    @Test
    void testSupplier() {
        Retry retry = Retry.ofDefaults("Gayuh");

        Supplier<String> supplier = Retry.decorateSupplier(retry, this::hello);
        supplier.get();
    }

}
