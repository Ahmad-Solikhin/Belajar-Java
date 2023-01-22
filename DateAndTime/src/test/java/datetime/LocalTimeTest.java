package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class LocalTimeTest {
    @Test
    void create() {
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(10, 10, 10);
        LocalTime localTime2 = LocalTime.parse("10:20:30");

        System.out.println(localTime);
        System.out.println(localTime1);
        System.out.println(localTime2);
    }

    @Test
    void change(){
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.withMinute(24);

        System.out.println(localTime1);
    }

    @Test
    void modify(){
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.plusMinutes(23);

        System.out.println(localTime1);
    }

    @Test
    void get(){
        LocalTime localTime = LocalTime.now();

        System.out.println(localTime.getHour());
    }
}