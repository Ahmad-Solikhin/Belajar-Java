package lambda.app;

import java.util.function.Consumer;

public class ConsumerApp {

    public static void main(String[] args) {
        Consumer<String> consumer = nilai -> System.out.println("Hallo " + nilai);
        consumer.accept("Gayuh");
    }

}
