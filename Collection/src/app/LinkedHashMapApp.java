package app;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapApp {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("first", "Gayuh");
        map.put("second", "Ahmad");
        map.put("last", "Raharjo");

        for (var v : map.keySet()){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("value");
        for (var v : map.values()){
            System.out.println(v);
        }
    }
}
