package lambda.app;

import java.util.function.Supplier;

public class LazyParameterApp {
    public static void main(String[] args) {
        testScore(80, getName());
        testScoreLambda(70, LazyParameterApp::getName); //Lambda sebagai lazy parameter
    }

    public static void testScore(int score, String name){
        if (score > 80){
            System.out.println("Selamat " + name + ", Anda Lulus");
        }else {
            System.out.println("Coba Lagi");
        }
    }

    public static void testScoreLambda(int score, Supplier<String> name){
        if (score > 80){
            System.out.println("Selamat " + name.get() + ", Anda Lulus");
        }else {
            System.out.println("Coba Lagi");
        }
    }

    public static String getName(){
        System.out.println("Dipanggil");
        return "Gayuh";
    }
}
