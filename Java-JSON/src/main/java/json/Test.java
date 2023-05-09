package json;

public class Test {

    public static void main(String[] args) {

        int[] x = {11,10,10,5,10,15,20,10,7,11};

//        System.out.print(foo(x,8,18,3,6));
//        System.out.print(foo(x,10, 20, 0, 9));
//        System.out.print(foo(x,8, 18, 6, 3));
//        System.out.print(foo(x,20, 10, 0, 9));
//        System.out.print(foo(x,6, 7, 8, 8));
//        System.out.println();
        System.out.println("======================");

        System.out.print(h(1, "fruits"));
        System.out.print(h(2, "fruits"));
        System.out.print(h(5, "fruits"));
        System.out.println();
        int hemm = pow(2, 9831050005000007l);
        System.out.println(hemm);

//        System.out.print(h(pow(2, 1000000000000000l),"fruits"));
//        System.out.print(h(pow(2, 9831050005000007l),"fruits"));

    }

    public static int foo(int[] x, int a, int b, int i, int j){
        int k = j;
        int ct = 0;

        while (k > i-1){
            if (x[k] <= b && !(x[k] <= a)){
                ct = ct + 1;
            }
            k = k -1;
        }
        return ct;
    }

    public static String g(String str){
        int i=0;
        String new_str = "";
        while(i < (str.length() -1)){
            new_str += str.charAt(1 + i);
            i = i + 1;
        }
        return new_str;
    }

    public static String f(String str){
        if (str.length() == 0) {
            return "";
        }
        else if (str.length() == 1){
            return str;
        }
        else {
            return f(g(str)) + str.charAt(0);
        }
    }

    public static String h (int n, String str){
        while(n!=1){
            if (n%2==0){
                n= n/2;
            }
            else{
                n =3*n +1 ;
            }
            str = f(str);
        }
        return str;
    }

    public static int pow(int x, long y){
        System.out.println(x + " " + y);
        if (y==0) {
            return 1;
        } else{
            return x * pow(x, y-1);
        }
    }
}
