package stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.stream.Stream;

public class AggregateOperatorTest {

    @Test
    void testMax() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    @Test
    void testMin() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .min(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    @Test
    void testCount() {
        Long count = Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .count();

        System.out.println(count);
    }

    @Test
    void testSum() {
        var hasil = Stream.of(1,2,3,4,5)
                .reduce(0, Integer::sum);

        System.out.println(hasil);
    }

    @Test
    void testFaktorial() {
        var faktorial = Stream.of(1,2,3,4,5)
                .reduce(1, (v, i) -> v * i);

        System.out.println(faktorial);
    }
}
