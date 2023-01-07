package app;

import java.util.*;

public class LegacyCollection {
    public static void main(String[] args) {
        //vector
        List<String> vector = new Vector<>();

        //HashTable
        Map<String, String> hashTable = new Hashtable<>();

        //Stack LIFO
        Stack<String> stack = new Stack<>();
        stack.push("Satu");
        stack.push("Dua");

        for (String v = stack.pop(); v != null; v = stack.pop()){
            System.out.println(v);
        }
    }
}
