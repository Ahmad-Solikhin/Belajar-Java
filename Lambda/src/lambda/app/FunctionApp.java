package lambda.app;

import java.util.function.Function;

public class FunctionApp {

    public static void main(String[] args) {
        Function<String, Integer> length = value -> value.length();
        System.out.println(length.apply("Gayuh"));

        //Cara 4
        Function<String, Integer> length1 = String::length;
        System.out.println(length1.apply("Solikhin"));
    }
}
