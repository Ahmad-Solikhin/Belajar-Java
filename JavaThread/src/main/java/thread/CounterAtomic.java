package thread;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {

    private final AtomicLong value = new AtomicLong(0);

    public void increment(){
        this.value.incrementAndGet();
    }

    public Long getValue(){
        return this.value.get();
    }

}
