package validation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

public class ConstructorValidationTest extends AbstractValidatorTest{

    @Test
    void testValidateConstructorParameter() throws NoSuchMethodException {

        String firstName = "";
        String lastName = "";
        Integer age = null;

        Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, Integer.class);

        var violation = executableValidator
                .validateConstructorParameters(constructor, new Object[]{firstName, lastName, age});

        violation.forEach(a -> {
            System.out.println(a.getPropertyPath());
            System.out.println(a.getMessage());
            System.out.println("---------------");
        });

    }
}
