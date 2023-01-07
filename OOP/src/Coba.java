class Coba
{
    public static void main(String[] args) {

        var coba = new Coba();

        System.out.println(coba.coba());

        String a = "Semuanya";
        String c = "Sem";
        String d = "uanya";
        String b = c+d;

        int g = 2;
        int z = 1+1;

        int q = ugata(g,z);

        System.out.println(a.equals(b));
        System.out.println(q);
    }

    private String cobaLagi()
    {
        String satu = coba();

        return satu + ", Dua";
    }

    private static int ugata(int a, int b)
    {
        return a + b;
    }

    private String coba(){
        return "Satu";
    }
}