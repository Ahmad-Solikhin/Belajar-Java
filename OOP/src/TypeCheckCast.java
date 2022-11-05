class TypeCheckCast
{
    /**
     * Digunakan untuk casting tipe data object ke tipe data lainnya
     * Pake instance of untuk mengetahui apakah dia turunan dari sebuag class lainnya atau engga
     * Buat castingnya juga tinggal pake (nama object)
     * Ini cuma bisa dipake kalo sesama kelas turunan
     * */

    public static void main(String[] args) {
        Mamal mamal= new Mamal();
        mamal.name = null;
        sayHello(mamal);

        Cat cat = new Cat();
        cat.name = "Oyen";
        sayHello(cat);
    }

    static void sayHello(Mamal mamal)
    {
        if (mamal instanceof Cat){
            //Ini castingnya
            Cat cat = (Cat) mamal;
            System.out.println("Hello Kucing " + cat.name);
        }else {
            System.out.println("Hello Mamal " + mamal.name);
        }
    }
}

class Mamal{
    String name;
}

class Cat extends Mamal{

}