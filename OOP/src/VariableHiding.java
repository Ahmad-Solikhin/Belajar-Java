class VariableHiding
{
    /**
     * Castignya itu dari bawah keatas, gabisa dari atas ke bawah
     * */

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.name = "Gayuh";
        parent.doIt();
        System.out.println(parent.name);

        parent = new Child();
        parent.doIt();

        Child child = new Child();
        child.childName = "Ahmad";
        child.doIt();
    }
}

class Parent
{
    String name;

    void doIt()
    {
        System.out.println("Parent");
    }
}

class Child extends Parent
{
    String childName;

    void doIt()
    {
        System.out.println("Child");
    }
}