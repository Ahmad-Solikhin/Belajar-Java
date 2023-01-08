package lambda.app;

import java.util.Optional;
import java.util.function.Function;

public class Optionalapp {
    public static void main(String[] args) {
        sayHello("gayuh");
        sayHello(null);
    }

    public static void sayHello(String value){

        Optional<String> optional = Optional.ofNullable(value).map(String::toUpperCase);

        optional.ifPresent(nilai -> System.out.println("HELLO " + nilai));

        //atau
        System.out.println();
        System.out.println("If Present Or Else");
        Optional.ofNullable(value)
                .map(String::toUpperCase)
                .ifPresentOrElse(
                        n -> System.out.println("HELLO " + n),
                        () -> System.out.println("HELLO")
                );

        //atau
        System.out.println();
        System.out.println("Or Else");
        String upperName = Optional.ofNullable(value)
                .map(String::toUpperCase)
                .orElse("TEMAN");

        System.out.println("HELLO " + upperName);
    }
}
