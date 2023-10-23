package com.gayuh.sequence.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.SequencedSet;
import java.util.TreeSet;

public class SetTest {
    @Test
    void set() {
        SequencedSet<String> set = new TreeSet<>();
        set.add("Zulfani");
        set.add("Ahmad");
        set.add("Gayuh");

        Assertions.assertEquals("Ahmad", set.getFirst());

        SequencedSet<String> reversed = set.reversed();

        Assertions.assertEquals("Zulfani", reversed.getFirst());

        String name = set.removeLast();
        Assertions.assertEquals("Zulfani", name);
    }
}
