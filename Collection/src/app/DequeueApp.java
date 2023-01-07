package app;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeueApp {
    public static void main(String[] args) {
        Deque<String> linkedList = new LinkedList<>(); //Ini pake list
        Deque<String> arrayDequeue = new ArrayDeque<>(); //Ini pake Array yang kalo dah offer capacity bakal grow

        linkedList.offerLast("Ahmad");
        linkedList.offerLast("Solikhin");
        linkedList.offerLast("Gayuh");

        System.out.println((linkedList.pollFirst()));
        System.out.println((linkedList.pollFirst()));
        System.out.println((linkedList.pollFirst()));
    }
}
