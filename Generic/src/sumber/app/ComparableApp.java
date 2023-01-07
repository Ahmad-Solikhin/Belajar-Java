package sumber.app;

import sumber.Person;

import java.util.Arrays;

public class ComparableApp {
    public static void main(String[] args) {
        Person[] people = {
                new Person("Gayuh", "Indonesia"),
                new Person("Ahmad", "Indonesia"),
                new Person("Solikhin", "Indonesia")
        };

        Arrays.sort(people);

        System.out.println(Arrays.toString(people));
    }
}
