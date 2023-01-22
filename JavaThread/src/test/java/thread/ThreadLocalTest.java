package thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    @Test
    void test() throws InterruptedException{

        final Random random = new Random();
        final UserService userService = new UserService();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    userService.setUser("user " + index);
                    Thread.sleep(1_000 + random.nextInt(3_000));
                    userService.doAction();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executorService.awaitTermination(2, TimeUnit.MINUTES);
    }

}
