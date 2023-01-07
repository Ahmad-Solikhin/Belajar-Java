package app;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorApp {
    public static void main(String[] args) {
        List<String> names = List.of("Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh");

        Spliterator<String> split1 = names.spliterator();
        Spliterator<String> split2 = split1.trySplit();

        System.out.println(split1.estimateSize());
        System.out.println(split2.estimateSize());

        System.out.println();
        System.out.println("Foreach names");
        for (var v : names) System.out.println(v);

        System.out.println();
        System.out.println("forEachRemaining split1");
        split1.forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        System.out.println();
        System.out.println("forEachRemaining split2");
        split2.forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

    }
}
