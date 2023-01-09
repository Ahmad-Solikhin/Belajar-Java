package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class LazyEvaluationTest {

    @Test
    void testIntermediateOperation() {
        List<String> list = List.of("Ahmad", "Solikhin");

        //Ini tetep lazy karnea operation map adalah intermediate
        Stream<String> upper = list.stream()
                .map(v -> {
                    System.out.println("Change " + v + " tp UPPER");
                    return v.toUpperCase();
                });
    }

    @Test
    void testTerminalOperation() {
        List<String> list = List.of("Ahmad", "Solikhin");

        //Ini tetep lazy karnea operation map adalah intermediate
        list.stream()
                .map(v -> {
                    System.out.println("Change " + v + " tp UPPER");
                    return v.toUpperCase();
                })
                //Operation forEach ini adalah terminal dan mengalirkan stream
                .forEach(System.out::println);
    }
}
