package validation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class MethodValidationTest extends AbstractValidatorTest{

    @Test
    void testSayHallo() throws NoSuchMethodException {
        Person person = new Person();

        String name = "";

        Method method = Person.class.getMethod("sayHello", String.class);

        var violation = executableValidator
                .validateParameters(person, method, new Object[]{name});

        violation.forEach(a -> {
            System.out.println(a.getPropertyPath());
            System.out.println(a.getMessage());
            System.out.println("---------------");
        });
    }

    @Test
    void fullName() throws NoSuchMethodException {
        Person person = new Person();
        person.setFirstName("");
        person.setLastName("");
        String returnValue = person.fullName();

        Method method = Person.class.getMethod("fullName");

        var violation = executableValidator.validateReturnValue(person, method, returnValue);

        violation.forEach(a -> {
            System.out.println(a.getPropertyPath());
            System.out.println(a.getMessage());
            System.out.println("---------------");
        });
    }
}
