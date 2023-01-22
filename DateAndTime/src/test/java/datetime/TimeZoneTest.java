package datetime;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class TimeZoneTest {

    @Test
    void testTimeZone(){

        TimeZone timeZoneDefault = TimeZone.getDefault();
        System.out.println("Default Time Zone");
        System.out.println(timeZoneDefault);

        System.out.println();
        System.out.println("Time Zone GMT");
        System.out.println(TimeZone.getTimeZone("GMT"));

        System.out.println();
        System.out.println("Avaliable Time Zone");
        List<String> avaliable = Arrays.asList(TimeZone.getAvailableIDs());
        avaliable.forEach(System.out::println);
    }
}
