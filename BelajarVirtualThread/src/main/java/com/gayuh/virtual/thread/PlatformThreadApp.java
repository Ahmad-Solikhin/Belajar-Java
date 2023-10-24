package com.gayuh.virtual.thread;

import java.time.Duration;

public class PlatformThreadApp {

    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) {
            Thread thread = Thread.ofPlatform().name("gayuh" + i).daemon(false).unstarted(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(2L));
                    System.out.println("Hello " + Thread.currentThread() + "!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.start();
        }
    }
}
