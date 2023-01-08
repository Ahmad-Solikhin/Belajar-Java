package lambda.app;

import lambda.util.StringUtil;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceApp {

    public static void main(String[] args) {
        MethodReferenceApp main = new MethodReferenceApp();
        Predicate<String> stringPredicate = StringUtil::isLower;

        System.out.println(stringPredicate.test("gayuh"));
        System.out.println(stringPredicate.test("Gayuh"));

        //Method reference di parameter
        Function<String, String> function = String::toUpperCase;

        System.out.println(function.apply("gayuh"));

        main.run();
    }

    public void run(){
        MethodReferenceApp app = new MethodReferenceApp(); //Kalo menggunakan class org lain
        Predicate<String> stringPredicate = app::isLower;
//        Predicate<String> stringPredicate = this::isLower; Kalo pake method di class yang sama

        System.out.println(stringPredicate.test("gayuh"));
        System.out.println(stringPredicate.test("Gayuh"));
    }

    public boolean isLower(String value){
        for (var v : value.toCharArray()){
            if (!Character.isLowerCase(v)){
                return false;
            }
        }
        return true;
    }
}
