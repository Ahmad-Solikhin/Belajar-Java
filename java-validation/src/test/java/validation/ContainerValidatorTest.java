package validation;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ContainerValidatorTest {

    @Test
    void test(){
        Person person = new Person();

        List<String> hobbies = List.of("Tidur", "Rebahan");
        person.setHobbies(hobbies);

    }
}
