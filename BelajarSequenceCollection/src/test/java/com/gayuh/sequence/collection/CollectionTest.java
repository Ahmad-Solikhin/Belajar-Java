package com.gayuh.sequence.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class CollectionTest {

    @Test
    void collection() {
        //Langsung pake List aja juga udah implementasi SequencedCollection
        SequencedCollection<String> list = new ArrayList<>();
        list.add("Ahmad"); // Ahmad
        list.add("Solikhin"); //Ahmad, Solikhin
        list.add("Gayuh"); //Ahmad, Solikhin, Gayuh
        list.add("Raharjo"); //Ahmad, Solikhin, Gayuh, Raharjo

        Assertions.assertEquals(List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo"), list);

        Assertions.assertEquals("Ahmad", list.getFirst());
        Assertions.assertEquals("Raharjo", list.getLast());

        SequencedCollection<String> reversed = list.reversed();
        Assertions.assertEquals(List.of("Raharjo", "Gayuh", "Solikhin", "Ahmad"), reversed);

        String firstName = list.removeFirst();
        Assertions.assertEquals("Ahmad", firstName);

        String lastName = list.removeLast();
        Assertions.assertEquals("Raharjo", lastName);

        Assertions.assertEquals("Solikhin", list.getFirst());
        Assertions.assertEquals("Gayuh", list.getLast());
    }
}
