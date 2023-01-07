package app;

import java.util.HashMap;
import java.util.Map;

public class HashMapApp {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("First", "Ahmad");
        map.put("Second", "Gayuh");
        map.put("Last", "Raharjo");

        System.out.println(map.get("Last"));
    }
}
