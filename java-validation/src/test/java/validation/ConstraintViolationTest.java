package validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConstraintViolationTest extends AbstractValidatorTest{

    @Test
    void testValidationFailed(){
        Person person = new Person();
        person.setFirstName("Ahmad Solikhin Gayuh Raharjo Yang Maish Hidup Tapi Sering Dianggap Ga Ada");

        Set<ConstraintViolation<Person>> violation = validator.validate(person);
        Assertions.assertEquals(3, violation.size());

        violation.forEach(a -> {
            System.out.println("Message: " + a.getMessage());
            System.out.println("Object/Bean: " + a.getLeafBean());
            System.out.println("Constraint: " + a.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value Field: " + a.getInvalidValue());
            a.getPropertyPath().forEach(v -> System.out.println("Path: " + v.getName()));
            System.out.println();
        });
    }

    @Test
    void testValidationSuccess(){
        Person person = new Person("Ahmad Solikhin Gayuh", "Raharjo", 21);

        Set<ConstraintViolation<Person>> violation = validator.validate(person);
        Assertions.assertEquals(0, violation.size());

        violation.forEach(a -> {
            System.out.println("Message: " + a.getMessage());
            System.out.println("Object/Bean: " + a.getLeafBean());
            System.out.println("Constraint: " + a.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value Field: " + a.getInvalidValue());
            a.getPropertyPath().forEach(v -> System.out.println("Path: " + v.getName()));
            System.out.println();
        });
    }
}
