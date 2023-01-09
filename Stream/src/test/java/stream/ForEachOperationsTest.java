package stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class ForEachOperationsTest {
    @Test
    void testPeek() {
        Stream.of("Ahmad", "Solikhin", "Gayuh")
                .peek(name -> System.out.println("Before Upper " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("After Upper " + name))
                .forEach(System.out::println);
    }
}
