package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySerachListApp {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(1_000);

        for (int i = 1; i <= 1_000 ; i++) {
            list.add(i);
        }

        Integer index = Collections.binarySearch(list, 189);

        System.out.println(index);

        System.out.println();
        System.out.println("Cari dari Belakang");
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };

        list.sort(comparator);

        Integer index2 = Collections.binarySearch(list, 189, comparator);
        System.out.println(index2);
        System.out.println(list.get(index2));


    }
}
