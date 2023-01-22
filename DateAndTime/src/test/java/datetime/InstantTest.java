package datetime;

import org.junit.jupiter.api.Test;

import java.time.*;

public class InstantTest {

    @Test
    void create(){
        Instant instant = Instant.now();
        Instant instant1 = Instant.ofEpochMilli(0);

        //cara konversi susah
        String a = instant.toString();
        a = a.replace("Z", "");
        LocalDateTime localDateTime = LocalDateTime.parse(a);

        //Cara mudah
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(7));

        System.out.println(instant);
        System.out.println(instant1);
        System.out.println(localDateTime);
        System.out.println(a);
        System.out.println(localDateTime1);
    }

    @Test
    void konversi(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant instant = offsetDateTime.toInstant();
        Instant instant1 = localDateTime.toInstant(ZoneOffset.ofHours(7));

        System.out.println(instant);
        System.out.println(instant1);
    }
}
