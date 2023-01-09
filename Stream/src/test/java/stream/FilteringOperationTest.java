package stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class FilteringOperationTest {

    @Test
    void testFilter() {
        List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo").stream()
                .filter(v -> v.length() > 5)
                .forEach(System.out::println);

        List.of(1,2,3,4,5,6,7,8).stream()
                .filter(v -> v % 2 == 0)
                .forEach(System.out::println);
    }

    @Test
    void testDistinct() {
        Stream.of("Ahmad", "Solikhin", "Solikhin", "Gayuh", "Raharjo")
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void coba() {
        Set.of("Ahmad", "Solikhin", "Gayuh").stream()
                .filter(v -> v.length() > 4)
                .forEach(System.out::println);
    }
}
