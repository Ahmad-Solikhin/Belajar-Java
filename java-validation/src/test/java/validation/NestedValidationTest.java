package validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NestedValidationTest extends AbstractValidatorTest {

    @Test
    void testNested(){
        Person person = new Person("Ahmad Solikhin", "Gayuh Raharjo", 21);
        Address address = new Address();
        person.setAddress(address);

        var violation = validator.validate(person);
        violation.forEach(a -> {
            System.out.println(a.getMessage());
            System.out.println(a.getPropertyPath());
            System.out.println();
        });
    }
}
