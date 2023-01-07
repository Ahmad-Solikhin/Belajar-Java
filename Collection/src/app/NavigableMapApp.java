package app;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapApp {
    public static void main(String[] args) {
        NavigableMap<String, String> map = new TreeMap<>();

        map.put("a", "1");
        map.put("z", "26");
        map.put("c", "3");

        for (var v : map.keySet()){
            System.out.println(v);
        }

        System.out.println(map.lowerKey("c"));
        System.out.println(map.higherKey("a"));

        NavigableMap<String, String> desc = map.descendingMap();

        for (var v : desc.keySet()){
            System.out.println(v);
        }

        //Immutable NavigableMap
        NavigableMap<String, String> empty = Collections.emptyNavigableMap();
        NavigableMap<String, String> immutable = Collections.unmodifiableNavigableMap(map);
    }
}
