package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class TransformationOperationTest {

    @Test
    void testMap() {
        List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo").stream()
                .map(String::toUpperCase)
                .map(String::length)
                .forEach(System.out::println);
    }

    @Test
    void testFlatMapOperation() {
        List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo").stream()
                .map(String::toUpperCase)
                .flatMap(v -> Stream.of(v, v.length()))
                .forEach(System.out::println);
    }
}
