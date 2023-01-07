package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntryApp {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("a", "1");
        map.put("c", "3");
        map.put("b", "2");

        Set<Map.Entry<String, String>> entries = map.entrySet();

        System.out.println(entries);

        for (var v : entries){
            System.out.println(v.getKey() + " : " + v.getValue());
        }
    }
}
