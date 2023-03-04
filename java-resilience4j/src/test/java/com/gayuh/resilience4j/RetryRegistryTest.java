package com.gayuh.resilience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@Slf4j
public class RetryRegistryTest {

    void callMe() {
        log.info("Try call me");
        throw new IllegalArgumentException("Ups error");
    }

    @Test
    void test(){
        RetryRegistry registry = RetryRegistry.ofDefaults();

        Retry retry1 = registry.retry("gayuh");
        Retry retry2= registry.retry("gayuh");

        Assertions.assertSame(retry1, retry2);
    }

    @Test
    void testRegistryConfig(){
        RetryConfig config1 = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions()
                .build();

        RetryRegistry registry = RetryRegistry.ofDefaults();
        registry.addConfiguration("config1", config1);

        Retry retry1 = registry.retry("gayuh", config1);
        Retry retry2 = registry.retry("gayuh", config1);

        Assertions.assertSame(retry1, retry2);

        Retry.decorateRunnable(retry2, this::callMe).run();
    }

}
