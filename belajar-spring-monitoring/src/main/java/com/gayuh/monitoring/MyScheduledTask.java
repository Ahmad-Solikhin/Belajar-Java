package com.gayuh.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MyScheduledTask {
    Logger log = LoggerFactory.getLogger(MyScheduledTask.class);

    private MeterRegistry meterRegistry;

    MyScheduledTask(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Scheduled(fixedRate = 10L,timeUnit = TimeUnit.SECONDS)
    public void hello(){
        meterRegistry.counter("my.scheduled.task").increment();
        log.info("Hello Ges");
    }
}
