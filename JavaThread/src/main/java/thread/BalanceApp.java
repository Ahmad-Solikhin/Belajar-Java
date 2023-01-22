package thread;

public class BalanceApp {
    public static void main(String[] args) {
        //Kondisi Deadlock
        Balance balance1 = new Balance(1_000_000L);
        Balance balance2 = new Balance(1_000_000L);

        Thread thread1 = new Thread(() -> {
            try {
                Balance.transferNoDeadlock(balance1, balance2, 500_000L);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Balance.transferNoDeadlock(balance2, balance1, 500_000L);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());
    }
}
