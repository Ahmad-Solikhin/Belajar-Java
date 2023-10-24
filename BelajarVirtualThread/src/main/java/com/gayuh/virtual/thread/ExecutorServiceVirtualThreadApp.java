package com.gayuh.virtual.thread;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceVirtualThreadApp {

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10_000; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(2L));
                    System.out.println("Hello " + Thread.currentThread() + "!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.in.read();
        executor.close();
    }
}
