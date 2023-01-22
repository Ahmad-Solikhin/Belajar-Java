package thread;

public class SynchronizedCounter {
    private Long value = 0L;

    public synchronized void increment(){
        this.value++;
    }

    public Long getValue(){
        return  this.value;
    }
}
