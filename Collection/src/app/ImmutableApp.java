package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableApp {
    public static void main(String[] args) {

        List<String> one = Collections.singletonList("Satu"); //1 data
        List<String> kosong = Collections.emptyList();//Kosong

        List<String> mutable = new ArrayList<>();
        mutable.add("Gayuh");
        mutable.add("Human");

        List<String> immutable = Collections.unmodifiableList(mutable); //Konversi

        List<String> elements = List.of("Gayuh", "Human"); // elements

    }
}
