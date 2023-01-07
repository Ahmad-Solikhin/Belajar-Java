package app.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {

    private String name;
    private List<String> hobies;

    public Person(String name) {
        this.name = name;
        this.hobies = new ArrayList<>();
    }

    public void addHobby(String hobby){
        this.hobies.add(hobby);
    }

    public List<String> getHobby(){
        //return list nya jadi immutable agar tidak bisa diakses setelah dipanggil

        return Collections.unmodifiableList(this.hobies);
    }

    public String getName() {
        return name;
    }
}
