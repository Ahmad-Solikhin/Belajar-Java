package lambda.app;

import java.util.function.Predicate;

public class PredicateApp {
    public static void main(String[] args) {
        Predicate<String> predicate = value -> value.isBlank();
        System.out.println(predicate.test("Gayuh"));
        System.out.println(predicate.test(""));

        Predicate<String> predicate1 = String::isBlank;
        System.out.println(predicate1.test("Gayuh"));
        System.out.println(predicate1.test(""));
    }
}
