package com.gayuh.sequence.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MapTest {

    @Test
    void map() {
        SequencedMap<Integer, String> map = new TreeMap<>();
        map.put(1, "Ahmad");
        map.put(3, "Gayuh");
        map.put(2, "Solikhin");

        Assertions.assertEquals("Gayuh", map.lastEntry().getValue());
        Assertions.assertEquals("Ahmad", map.firstEntry().getValue());

        var reversed = map.reversed();
        Assertions.assertEquals("Gayuh", reversed.firstEntry().getValue());
        Assertions.assertEquals("Ahmad", reversed.lastEntry().getValue());
    }

    @Test
    void linkedMap() {
        var linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.putFirst("b", "Gayuh");
        linkedHashMap.putFirst("z", "Ahmad");
        linkedHashMap.putLast("a", "Solikhin");

        Assertions.assertEquals("Ahmad", linkedHashMap.firstEntry().getValue());
        Assertions.assertEquals("Solikhin", linkedHashMap.lastEntry().getValue());
        Assertions.assertEquals("a", linkedHashMap.lastEntry().getKey());
    }
}
