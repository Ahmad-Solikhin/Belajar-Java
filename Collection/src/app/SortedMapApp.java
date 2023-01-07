package app;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapApp {
    public static void main(String[] args) {
        SortedMap<String, String> map = new TreeMap<>(); //Parameter bisa diisi comparator kalo tidak implements comparable

        map.put("a", "1");
        map.put("c", "3");
        map.put("b", "2");

        for (var v : map.keySet()){
            System.out.println(v);
        }

        SortedMap<String, String> immutable = Collections.unmodifiableSortedMap(map);
    }
}
