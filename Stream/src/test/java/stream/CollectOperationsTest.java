package stream;

import com.sun.source.doctree.SeeTree;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectOperationsTest {

    Stream<String> getStream() {
        List<String> list = new ArrayList<>();
        list.addAll(List.of("Ahmad", "Solikhin", "Gayuh"));
        list.add("Raharjo");

        return list.stream();
    }
    @Test
    void testCollect() {
        Set<String> set = getStream().collect(Collectors.toSet());
        Set<String> immutableSet = getStream().collect(Collectors.toUnmodifiableSet());
        System.out.println(set);

        List<String> list = getStream().toList();
        List<String> immutableList = Collections.unmodifiableList(list);

        System.out.println(list);
    }

    //Membuat map dari stream
    @Test
    void testcollectorMap() {
        Map<Integer, String> map = getStream().collect(
                Collectors.toMap(
                        String::hashCode,
                        v -> v
                ));
        System.out.println(map);
    }
}
