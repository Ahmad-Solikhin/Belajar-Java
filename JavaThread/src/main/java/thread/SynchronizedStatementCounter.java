package thread;

public class SynchronizedStatementCounter {
    private Long value = 0L;

    public void increment(){
        synchronized (this){
            this.value++;
        }
    }

    public Long getValue(){
        return  this.value;
    }
}
