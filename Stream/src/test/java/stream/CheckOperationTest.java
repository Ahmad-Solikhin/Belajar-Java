package stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class CheckOperationTest {
    @Test
    void testAnyMatch() {
        var match = Stream.of(1,2,3,4,5,6)
                .anyMatch(num -> num < 2);

        System.out.println(match);
    }

    @Test
    void testAllMatch() {
        var match = Stream.of(1,2,3,4,5,6)
                .allMatch(num -> num > 0);

        System.out.println(match);
    }

    @Test
    void testNoneMatch() {
        var match = Stream.of(1,2,3,4,5,6)
                .noneMatch(num -> num < 2);

        System.out.println(match);
    }
}
