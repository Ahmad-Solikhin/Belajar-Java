package datetime;

import org.junit.jupiter.api.Test;

import java.time.*;

public class CloclTest {
    @Test
    void create() {
        Clock clock = Clock.systemDefaultZone();
        Clock clock1 = Clock.system(ZoneOffset.ofHours(5));

        System.out.println(clock);
        System.out.println(clock1);
    }

    @Test
    void instant() throws Throwable{
        Clock clockJakarta = Clock.system(ZoneId.of("Asia/Jakarta"));

        Instant instant = clockJakarta.instant();
        System.out.println(instant);

        Thread.sleep(1_000);

        Instant instant1 = clockJakarta.instant();
        System.out.println(instant1);

    }

    @Test
    void konversi(){
        Clock clockJakarta = Clock.system(ZoneId.of("Asia/Jakarta"));

        LocalDateTime localDateTime = LocalDateTime.now(clockJakarta);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(clockJakarta);
        OffsetDateTime offsetDateTime = OffsetDateTime.now(clockJakarta);

        System.out.println(localDateTime);
        System.out.println(zonedDateTime);
        System.out.println(offsetDateTime);
    }
}
