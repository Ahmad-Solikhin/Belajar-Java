package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeTest {

    @Test
    void creat(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2001, Month.OCTOBER, 7, 10, 10, 10);
        LocalDateTime localDateTime2 = LocalDateTime.parse("2001-10-04T10:10:10");

        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
    }

    @Test
    void change(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.withYear(2024);

        System.out.println(localDateTime1);
    }

    @Test
    void modify(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusYears(1).plusMonths(12);

        System.out.println(localDateTime1);
    }

    @Test
    void get(){
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getDayOfWeek());
    }

    @Test
    void date(){
        LocalDateTime localDateTime = LocalDateTime.now();

        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDateTime);
        System.out.println(localDate);

        System.out.println();

        LocalDateTime result = localDate.atTime(LocalTime.now());

        System.out.println(result);
    }

    @Test
    void time(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        LocalDateTime result = localTime.atDate(LocalDate.of(2024, Month.JANUARY, 16));
        System.out.println(result);
    }
}
