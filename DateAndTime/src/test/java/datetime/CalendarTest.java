package datetime;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

    @Test
    void create(){
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        System.out.println(date);
    }

    @Test
    void manipulateCalendar(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2001);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 20);

        Date date = calendar.getTime();

        System.out.println(date);
    }
}
