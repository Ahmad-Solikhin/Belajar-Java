import java.lang.reflect.Array;

class Identitiy extends Animal{
    private String name;

    Identitiy(String jenis, String name)
    {
        this.setJenis(jenis);
        this.name = name;
    }

    Identitiy(String name){
        this(null, name);
    }

    Identitiy(){
        this(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Identitiy{" +
                "name='" + this.getName() + '\'' +
                "jenis='" + this.getJenis() + '\'' +
                "kaki='" + this.getKaki() + '\'' +
                "habitat='" + this.getHabitat() + '\'' +
                "kaum='" + this.getKaum() + '\'' +
                '}';
    }

    public void sayHello(String nama)
    {
        System.out.println("Hello " + nama +", my name is " + this.name);
    }
}
