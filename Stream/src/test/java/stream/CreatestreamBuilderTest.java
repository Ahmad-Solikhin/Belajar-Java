package stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class CreatestreamBuilderTest {

    @Test
    void testCreateStreamBuilder() {
        Stream.Builder<String>  builder = Stream.builder();
        builder.accept("Gayuh");
        builder.add("Solikhin").add("Raharjo");

        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);
    }

    @Test
    void testCreateStreamBuilderSimplify() {
        Stream<Object> stream = Stream.builder()
                .add("Ahmad").add("Solikhin").build();

        stream.forEach(System.out::println);
    }
}
