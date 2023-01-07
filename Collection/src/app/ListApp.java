package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListApp {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>(); //Parameter bisa diisi dengan initiate capacity. default 10
        List<String> linkedList = new LinkedList<>(); //Implementasi linkedlist sama aja methodnya kayak arraylist

        arrayList.add("Ahmad");
        arrayList.addAll(List.of("saasa", "Gayuh", "Raharjo"));
        System.out.println("Sebelum di Set");
        for (var v : arrayList){
            System.out.println(v);
        }
        System.out.println();
        System.out.println("Setelah di Set");
        arrayList.set(1, "Solikhin");

        for (var v : arrayList){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Ambil Data Index ke-2");;
        System.out.println(arrayList.get(2));

        System.out.println();
        System.out.println("Hpus nama Ahmad");
        arrayList.remove("Ahmad");
        for (var v : arrayList){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("Hpus nama Gayuh");
        arrayList.remove(1);
        for (var v : arrayList){
            System.out.println(v);
        }
    }
}
