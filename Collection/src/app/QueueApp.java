package app;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueApp {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>(10);
        Queue<String> priority = new PriorityQueue<>(10);

        priority.add("Human");
        priority.add("Ahmad");
        priority.add("Gayuh");

        for (String next = priority.poll(); next != null; next = priority.poll()){
            System.out.println(next);
        }
    }
}
