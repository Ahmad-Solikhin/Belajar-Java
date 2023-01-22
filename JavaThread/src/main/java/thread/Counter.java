package thread;

public class Counter {
    private Long value = 0L;

    public void increment(){
        this.value++;
    }

    public Long getValue(){
        return  this.value;
    }
}
