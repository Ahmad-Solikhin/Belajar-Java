package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamPipelineTest {

    @Test
    void testCtreateStreamPipeline() {
        List<String> list = List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo");

        //Contoh penggunaan stream pipeline
        list.stream()
                .map(String::toUpperCase)
                .map(up -> "Mr. " + up)
                .forEach(System.out::println);
    }
}
