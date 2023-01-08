package lambda.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapForEach {
    public static void main(String[] args) {
        Map<String, String > map = new HashMap<>();

        map.putAll(Map.of(
                "first", "ahmad",
                "second", "Solikhin",
                "last", "Raharjo"
        ));

        //for loop
        System.out.println("For loop");
        for (var v : map.entrySet()){
            System.out.println(v.getKey() +" : " + v.getValue());
        }

        //Anonymous
        System.out.println();
        System.out.println("Anonymous");
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s + " : " + s2);
            }
        });

        //Lambda
        System.out.println();
        System.out.println("Lambda");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
