package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ReactiveStreamTest {

    @Test
    void publisher() throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        PrintSubscriber subscriber1 = new PrintSubscriber("A", 1_000L);
        publisher.subscribe(subscriber1);
        PrintSubscriber subscriber2 = new PrintSubscriber("B", 500L);
        publisher.subscribe(subscriber2);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            for (int i = 0; i < 1_00; i++) {
                publisher.submit("Gayuh-" + i);
                System.out.println(Thread.currentThread().getName() + " : Send data-" + i);
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        Thread.sleep(100 * 1_000);
    }

    public static class PrintSubscriber implements Flow.Subscriber<String>{

        private Flow.Subscription subscription;

        private String name;

        private Long sleep;

        public PrintSubscriber(String name, Long sleep) {
            this.name = name;
            this.sleep = sleep;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
            //Jika tidak ingin mengambil data bisa
            //this.subscription.cancel();
        }

        @Override
        public void onNext(String item) {
            try {
                Thread.sleep(sleep);
                System.out.println(Thread.currentThread().getName() + " : " + name + " : " + item);
                this.subscription.request(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println(Thread.currentThread().getName() + " : Done");
        }
    }
}
