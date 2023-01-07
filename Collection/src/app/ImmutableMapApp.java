package app;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMapApp {
    public static void main(String[] args) {
        Map<String, String> empty = Collections.emptyMap();

        Map<String, String> single = Collections.singletonMap("name", "Gayuh");

        Map<String, String> mutable = new HashMap<>();
        mutable.put("name", "Gayuh");
        Map<String, String> immutable = Collections.unmodifiableMap(mutable);

        Map<String, String> elements = Map.of(
                "first", "Gayuh",
                "second", "Ahmad"
        );
    }
}
