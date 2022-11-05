class Coba
{
    public static void main(String[] args) {

        var coba = new Coba();

        System.out.println(coba.coba());
    }

    private String cobaLagi()
    {
        String satu = coba();

        return satu + ", Dua";
    }

    private String coba(){
        return "Satu";
    }
}