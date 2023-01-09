package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingByTest {

    @Test
    void testGroupingBy() {
        Map<String, List<Integer>> map = Stream.of(1,2,3,4,5,6,7,8,9,10)
                .collect(Collectors.groupingBy(n -> n > 5 ? "Besar" : "Kecil"));

        System.out.println(map);

    }

    @Test
    void testGorupingBy2() {
        Map<Integer, List<String>> map = Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .collect(Collectors.groupingBy(n -> n.hashCode()));
        //Strukturnya itu groupingBy(value -> key);

        System.out.println(map);
    }

    @Test
    void testPartitioningBy() {
        Map<Boolean, List<String>> map = Stream.of("Ahmad", "Solikhin", "Gayuh", "Raharjo")
                .collect(Collectors.partitioningBy(n -> n.length() > 5));
        //Strukturnya itu groupingBy(value -> key);

        System.out.println(map);
    }
}
