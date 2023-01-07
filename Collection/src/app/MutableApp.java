package app;

import app.data.Person;

public class MutableApp {
    public static void main(String[] args) {
        Person person = new Person("Gayuh");

        person.addHobby("Tidur");
        person.addHobby("Makan");

        for (var v : person.getHobby()){
            System.out.println(v);
        }
    }
}
