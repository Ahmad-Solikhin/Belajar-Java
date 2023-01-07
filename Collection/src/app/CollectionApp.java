package app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionApp {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        //Tambah Data
        collection.add("Ahmad");
        collection.add("Solikhin");
        collection.addAll(List.of("Gayuh", "Raharjo"));

        for (var v : collection){
            System.out.println(v);
        }

        System.out.println();
        //Hapus data
        collection.remove("Gayuh");
        collection.removeAll(List.of("Solikhin", "Ahmad"));

        for (var v : collection){
            System.out.println(v);
        }

        System.out.println();
        //check data di collection
        System.out.println(collection.contains("Raharjo"));
        System.out.println(collection.contains("Gayuh"));
        System.out.println(collection.containsAll(List.of("Raharjo", "Gayuh")));
    }
}
