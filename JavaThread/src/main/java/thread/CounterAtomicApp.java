package thread;

public class CounterAtomicApp {

    public static void main(String[] args) {
        CounterAtomic counter = new CounterAtomic();

        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                //System.out.println("Dikerjakan oleh thread " + Thread.currentThread().getName());
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(counter.getValue());
    }
}
