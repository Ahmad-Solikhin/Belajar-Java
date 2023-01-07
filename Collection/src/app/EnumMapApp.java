package app;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapApp {

    public static enum Level {
        BASIC, PREMIUM, FREE
    }

    public static void main(String[] args) {
        Map<Level, String> map = new EnumMap<Level, String>(Level.class);
        map.put(Level.FREE, "Gratis");
        map.put(Level.BASIC, "Biasa");
        map.put(Level.PREMIUM, "Premium");

        for (var v : map.keySet()){
            System.out.println(map.get(v));
        }
    }
}
