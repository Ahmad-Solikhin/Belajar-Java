package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamOperationTest {

    @Test
    void testStreamOperation() {
        List<String> names = List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo");

        Stream<String> streamName = names.stream();
        Stream<String> streamUpper = streamName.map(String::toUpperCase);

        streamUpper.forEach(System.out::println);
        names.forEach(System.out::println);
    }
}
