package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

public class PriodTest {

    @Test
    void create() {
        Period period = Period.ofDays(10);
        Period period1 = Period.ofWeeks(1);
        Period period2 = Period.of(0, 1, 0);

        System.out.println(period.getDays());
        System.out.println(period1.getDays());
        System.out.println(period2);
    }

    @Test
    void between() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2001, 10, 7);

        Period period = Period.between(localDate1, localDate);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }
}
