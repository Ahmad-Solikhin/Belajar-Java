package app;

import java.util.Arrays;
import java.util.List;
public class ToArrayApp {

    public static void main(String[] args) {
        List<String> names = List.of("Ahmad", "Solikhin", "Gayuh", "Raharjo");

        Object[] array1 = names.toArray();
        String[] array2 = names.toArray(new String[]{});

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
