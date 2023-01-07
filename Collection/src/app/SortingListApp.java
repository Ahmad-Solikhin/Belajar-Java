package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingListApp {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.addAll(List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo"));

        Collections.sort(list);

        for (var v : list){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Comparator Sendiri");
        //Membuat comparator sendiri
        Comparator<String> reverse = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        };

        list.sort(reverse);

        for (var v : list){
            System.out.println(v);
        }
    }
}
