package app;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetApp {
    public static void main(String[] args) {
        System.out.println("HashSet");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Ahmad");
        hashSet.add("Gayuh");

        for (var v : hashSet){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("LinkedHashSet");
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Ahmad");
        linkedSet.add("Gayuh");

        for (var v : linkedSet){
            System.out.println(v);
        }
    }
}
