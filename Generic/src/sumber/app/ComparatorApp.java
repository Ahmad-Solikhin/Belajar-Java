package sumber.app;

import sumber.Person;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorApp {

    public static void main(String[] args) {
        Person[] people = {
                new Person("Gayuh", "Indonesia"),
                new Person("Ahmad", "Indonesia"),
                new Person("Solikhin", "Indonesia")
        };

        //Membuat Comparator
        Comparator<Person> reverse =
                (o1, o2) -> o2.getName().compareTo(o1.getName());

        Arrays.sort(people, reverse);

        System.out.println(Arrays.toString(people));
    }
}
