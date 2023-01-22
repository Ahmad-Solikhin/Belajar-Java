package lombok;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonTest {

    @Test
    void create(){
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Tidur");
        hobbies.add("Rebahan");

        Person person1 = new Person("1", "Gayuh", 21, hobbies);

        System.out.println(person1);
    }

    @Test
    void createSetter(){
        Person person = new Person();
        person.setId("2");
        person.setName("Gayuh");
        person.setAge(21);
        person.setHobbies(List.of("Tidur", "Rebahan"));

        System.out.println(person);
    }

    @Test
    void createViaBuilder(){

        List<String> hobbies = new ArrayList<>();
        hobbies.add("Tidur");
        hobbies.add("Rebahan");

        Person person = Person.builder()
                .id("10")
                .age(21)
                .name("Gayuh")
                .hobbies(hobbies)
                .build();

        System.out.println(person);
    }

    @Test
    void createViaBuilderAndSingular(){

        Person person = Person.builder()
                .id("10")
                .age(21)
                .name("Gayuh")
                .hobby("Rebahan")
                .hobby("Tidur")
                .build();

        System.out.println(person);
    }
}
