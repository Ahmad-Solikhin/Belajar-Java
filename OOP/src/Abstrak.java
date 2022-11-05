/**
 * Abstrak class yang gabisa dibuat object secara langsung melainkan harus diturunkan dulu ke class lain
 * Dalam abstrak class dapat dibuat semua isinya kayak class biasa
 * Bisa dibuat abstrak method juga yang ada di abstrak class, tapi gaboleh ada blocknya, cuma boleh ada nama methodnya dan parameternya
 * Karena abstrak harus diturunkan ke kelas lain maka gaboleh private
 * */

public abstract class Abstrak {
    public abstract void run(int number);
}
