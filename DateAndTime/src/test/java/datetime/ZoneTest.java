package datetime;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

public class ZoneTest {

    @Test
    void create(){
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);

        ZoneId zoneId1 = ZoneId.of("GMT");
        System.out.println(zoneId1);

        Set<String> avaliable = ZoneId.getAvailableZoneIds();
        avaliable.forEach(System.out::println);
    }

    @Test
    void testZoneOffset(){
        ZoneOffset zoneOffset = ZoneOffset.of("+07:00");
        ZoneOffset zoneOffset1 = ZoneOffset.ofHours(-7);

        System.out.println(zoneOffset);
        System.out.println(zoneOffset1);
    }
}
