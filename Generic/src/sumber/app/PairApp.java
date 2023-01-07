package sumber.app;

import sumber.Pair;

public class PairApp {

    public static void main(String[] args) {
        Pair<String, Integer> a = new Pair<>("Gayuh", 21);

        System.out.println(a.getFirst());
        System.out.println(a.getSecond());
    }
}
