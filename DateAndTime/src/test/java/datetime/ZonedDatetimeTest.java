package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedDatetimeTest {

    @Test
    void create(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(7));
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneOffset.ofHours(0));

        ZonedDateTime zonedDateTime3 = ZonedDateTime.parse("2001-10-07T10:10:06+07:00[Asia/Jakarta]");
        ZonedDateTime zonedDateTime4 = ZonedDateTime.parse("2001-10-07T10:10:06+05:00");

        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
        System.out.println(zonedDateTime3);
        System.out.println(zonedDateTime4);
    }

    @Test
    void change(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameLocal(ZoneOffset.of("+00:00"));
        ZonedDateTime zonedDateTime2 = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Jakarta"));

        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
    }
}
