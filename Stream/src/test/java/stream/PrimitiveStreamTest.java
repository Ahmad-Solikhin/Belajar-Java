package stream;

import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamTest {
    @Test
    void testCreate() {
        IntStream intStream = IntStream.range(1, 100);
        var average = intStream.average();
        average.ifPresent(System.out::println);
//        intStream.forEach(System.out::println);

        LongStream longStream = LongStream.of(1,2,3,4);
        longStream.forEach(System.out::println);

        DoubleStream doubleStream = DoubleStream.builder().add(1.2).add(2.1).build();
        doubleStream.forEach(System.out::println);
    }

    @Test
    void testKonversi() {
        IntStream intStream = IntStream.range(1, 100);

        Stream<String> streamObj = intStream
                .mapToObj(n -> "Number " + n);

        streamObj.forEach(System.out::println);
    }
}
