package datetime;

import org.junit.jupiter.api.Test;

import java.time.*;

public class YearTest {

    @Test
    void create(){
        Year year1 = Year.now();
        Year year2 = Year.of(2001);

        YearMonth yearMonth1 = YearMonth.now();
        YearMonth yearMonth2 = YearMonth.parse("2020-07");

        MonthDay monthDay1 = MonthDay.now();
        MonthDay monthDay2 = MonthDay.parse("--10-07");

        System.out.println(monthDay2);
    }

    @Test
    void year(){
        Year year = Year.now();

        LocalDate localDate = year.atMonth(Month.JANUARY).atDay(19);
        System.out.println(localDate);
    }
}
