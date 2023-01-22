package datetime;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationTest {

    @Test
    void create(){
        Duration duration = Duration.ofHours(10);
        Duration duration1 = Duration.ofMinutes(10);

        System.out.println(duration);
        System.out.println(duration1);
    }

    @Test
    void to(){
        Duration duration = Duration.ofHours(10);

        System.out.println(duration.toMinutes());
    }

    @Test
    void between() {
        Duration durasi = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusHours(10));

        System.out.println(durasi.toHours());
    }
}
