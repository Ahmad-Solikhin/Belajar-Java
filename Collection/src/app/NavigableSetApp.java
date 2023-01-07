package app;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class NavigableSetApp {
    public static void main(String[] args) {

        NavigableSet<String> names = new TreeSet<>(
                Set.of(
                        "Ahmad",
                        "Solikhin",
                        "Gayuh",
                        "Raharjo"
                ));

        for (var v : names){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Reversed");
        NavigableSet<String> reverse = names.descendingSet();
        for (var v : reverse){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Get Data To Tail");
        NavigableSet<String> tail = names.tailSet("Raharjo", true);
        for (var v : tail){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Mutable NavigableSet");
        NavigableSet<String> mutable = new TreeSet<>(Set.of("Human", "Cat"));

        NavigableSet<String> immutable = Collections.unmodifiableNavigableSet(mutable);
        for (var v : immutable){
            System.out.println(v);
        }
    }
}
