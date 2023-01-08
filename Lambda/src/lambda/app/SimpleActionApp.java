package lambda.app;

import lambda.SimpleAction;

public class SimpleActionApp {
    public static void main(String[] args) {
        SimpleAction simpleAction = new SimpleAction() {
            @Override
            public String action(String name) {
                return name;
            }
        };

        System.out.println(simpleAction.action("Ahmad"));

        //Lambda
        //Cara 1
        System.out.println();
        System.out.println("Menggunakan Lambda");
        SimpleAction simpleAction1 = (String name) -> {return name;};
        System.out.println(simpleAction1.action("Gayuh"));

        //Cara 2
        System.out.println();
        SimpleAction simpleAction2 = (name) -> {
            return "Hallo " + name;
        };
        System.out.println(simpleAction2.action("Raharjo"));

        //Cara ke-3
        System.out.println();
        SimpleAction simpleAction3 = name -> "Hai " + name;
        System.out.println(simpleAction3.action("Solikhin"));
    }
}
