package lambda.app;

import java.util.List;
import java.util.function.Consumer;

public class ForEachCollectionApp {
    public static void main(String[] args) {
        List<String> names = List.of("Ahmad", "Solikhin", "Gayuh");

        //manual for each
        System.out.println("For each biasa");
        for (var v : names){
            System.out.println(v);
        }

        //default method
        System.out.println();
        System.out.println("Anonymous class");
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //lambda
        System.out.println();
        System.out.println("Lambda");
        names.forEach(value -> System.out.println(value));

        //Method reference
        System.out.println();
        System.out.println("Method Reference");
        names.forEach(System.out::println);
    }
}
