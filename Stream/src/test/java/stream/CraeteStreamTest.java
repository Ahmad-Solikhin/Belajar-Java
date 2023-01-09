package stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CraeteStreamTest {

    @Test
    void testCreateEmptyOrSingleStream() {
        Stream<String> emptyStream = Stream.empty();

        emptyStream.forEach(System.out::println);

        Stream<String> one = Stream.of("Gayuh");

        one.forEach(System.out::println);

        String data = null;
        Stream<String> emptuOrNotStream = Stream.ofNullable(data);
    }

    @Test
    void testcreateStreamFromArray() {
        Stream<String> arrayStream = Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo");

        String[] array = new String[]{
                "Ahmad", "Solikhin", "Gayuh"
        };

        Stream<String> fromArray = Arrays.stream(array);

        fromArray.forEach(System.out::println);

    }

    @Test
    void testCreateStreamFromCollection() {
        Collection<String> collection = List.of("Ahmad", "Solikhin");
        Stream<String> stream = collection.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void testCreateInfiniteStream() {
        Stream<String> stream = Stream.generate(() -> "Infinite Stream");
//        stream.forEach(System.out::println);

        Stream<Integer> iterate = Stream.iterate(1, value -> value + 1);
//        iterate.forEach(System.out::println);
    }
}
