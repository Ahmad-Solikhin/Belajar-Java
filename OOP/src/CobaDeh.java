public class CobaDeh {

    public CobaDeh(String nama) {
        this.nama = nama;
    }

    public CobaDeh(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public CobaDeh() {
    }

    private String nama;
    private int umur;

    public String kapital(String nama){
        return nama.toUpperCase();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}

class Lagi{
    public static void main(String[] args) {
        CobaDeh coba = new CobaDeh();
        coba.setNama("Gayuh");

        CobaDeh coba1 = new CobaDeh("Ridho");
    }
}
