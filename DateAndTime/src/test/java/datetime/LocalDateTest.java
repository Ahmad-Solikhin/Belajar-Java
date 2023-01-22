package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateTest {
    
    @Test
    void create(){
        LocalDate dateNow = LocalDate.now();
        LocalDate dateOf = LocalDate.of(2001, Month.OCTOBER, 7);
        LocalDate dateParse = LocalDate.parse("2001-10-04");

        System.out.println(dateNow);
        System.out.println(dateOf);
        System.out.println(dateParse);
    }

    @Test
    void with(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.withYear(2022).withMonth(10);

        System.out.println(localDate1);
    }

    @Test
    void modify(){
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.plusYears(1).minusMonths(1);

        System.out.println(localDate2);
    }

    @Test
    void get(){
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getYear());
    }
}
