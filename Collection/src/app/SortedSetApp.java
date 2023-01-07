package app;

import app.data.Person;
import app.data.PersonComparator;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;

public class SortedSetApp {
    public static void main(String[] args) {
        //reversed itu buat ngubah jadi descending
        SortedSet<Person> people = new TreeSet<>(new PersonComparator().reversed()); //Kalo bukan turunan dari comparable maka harus taro comparator di parameternya

        people.add(new Person("Gayuh"));
        people.add(new Person("Ahmad"));

        for (var v : people){
            System.out.println(v.getName());
        }

        Set<Person> immutablePeople = Collections.unmodifiableSortedSet(people);
    }
}
