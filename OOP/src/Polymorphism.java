class Polymorphism
{
    /**
     * Jadi kalo ada class turunan dari sebuah parent, tipe datanya bisa make parentnya namun object yang dibuat make child nya
     * Walaupun berubah jadi class lain, tapi gabisa ngakses field yang ada di childnya
     * Jadi ini gunanya bisa ngirim object ke parameter, misal tipe data yang dikasih di parameter adalah parent class nya maka semua child class nya bisa dimasukkin sana juga
     * */

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.namaEmployee = "Gayuh";
        cobaYa(employee);

        Manager manager= new Manager();
        manager.namaEmployee = "Ahmad";
        cobaYa(manager);

        VicePresident vp = new VicePresident();
        vp.namaEmployee = "Solikhin";
        cobaYa(vp);

        Gada gada = new Gada();
        cobaYa(gada);
    }

    static void cobaYa(Employee employee)
    {
        System.out.println("Hello " + employee.namaEmployee);
    }
}

class Employee{
    String namaEmployee;

    void sayHello()
    {
        System.out.println("Haloo, saya adalah employee");
    }
}

class Manager extends Employee{
    String namaManager;

    @Override
    void sayHello()
    {
        System.out.println("Haloo, saya adalah manager");
    }
}

class VicePresident extends Manager
{
    String namaVicePresident;

    @Override
    void sayHello()
    {
        System.out.println("Haloo, saya adalah VP");
    }
}

class Gada extends Employee{

}