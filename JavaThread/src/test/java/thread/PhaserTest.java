package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    void phaserAsCountDownLatch() throws InterruptedException {
        final Phaser phaser = new Phaser();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);
        //kalo register lagi itu bakal nambah jumlahnya
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Start task");
                    Thread.sleep(2_000);
                    System.out.println("End Task");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    phaser.arrive();
                }
            });
        }

        executorService.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("Done All Task");
        });

        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    void phaserAsCyclicBarrier() throws InterruptedException {
        final Phaser phaser = new Phaser();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);
        //kalo register lagi itu bakal nambah jumlahnya
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println("Done");
            });
        }

        executorService.execute(() -> {
            phaser.arriveAndAwaitAdvance();
            System.out.println("Ini beda Gayn");
        });

        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
