package stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.stream.Stream;

public class OrderingOperationTest {

    @Test
    void testSorted() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void testSortedComporator() {
        Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }


}
