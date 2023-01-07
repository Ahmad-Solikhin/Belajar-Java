/**
 * Interface ini sebenernya abstrak yang sebenernya, karena semua method itu otomatis abstrak
 * Di interface hanya boleh ada variable final aja
 * Mewariskan interface makenya implements
 * Semua di interface itu public sama abstrak
 * 1 child class bisa implements lebih dari 1 interface ga kayak class ke class
 * interface bisa diturunkan ke interface lainnya juga, tapi makenya extends dan bisa lebih dari 1
 * */

/**
 * Dengan adanya default method ini bisa menghindari terjadinya error jika menambahkan 1 method ke interface
 * Dengan adanya ini juga bisa dibuat konkrit class dengan syarat kata kunci default di depan methodnya
 * */

public class Interface {
    public static void main(String[] args) {
        Avanza avanza = new Avanza();
        avanza.drive();
        System.out.println(avanza.getTire());
        System.out.println(avanza.tol);

        System.out.println(avanza.toString());
    }
}

interface HasBrand
{
    String getBrand();

    default boolean isBig(){
        return false;
    }
}

interface Car extends HasBrand
{
    final boolean tol = true;
    void drive();
    int getTire();
}

class Avanza implements Car
{
    @Override
    public void drive() {
        System.out.println("Ini Mobil Avanza");;
    }

    @Override
    public int getTire() {
        return 4;
    }

    @Override
    public String getBrand() {
        return "Toyota";
    }

    @Override
    public String toString() {
        return "Mobil dengan brand " + getBrand() + " bertipe Avanza, beroda " + getTire() + " Dapat di tol? " + this.tol;
    }
}

class Bus implements Car
{
    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public boolean isBig() {
        return true;
    }

    @Override
    public void drive() {

    }

    @Override
    public int getTire() {
        return 8;
    }

}