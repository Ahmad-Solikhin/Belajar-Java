package stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class RetrievingOperationTest {
    @Test
    void testLimit() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    void testSkip() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    void testTakeWhile() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .takeWhile(v -> !(v.length() > 5))
                .forEach(System.out::println);
    }

    @Test
    void testDropWhile() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .dropWhile(v -> v.length() < 6)
                .forEach(System.out::println);
    }

    @Test
    void testFindAny() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .findAny()
                .ifPresent(System.out::println);
    }

    @Test
    void testFindFirst() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .findFirst()
                .ifPresent(System.out::println);
    }
}
