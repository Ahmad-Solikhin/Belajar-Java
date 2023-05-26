package com.gayuh.SpringValidation;

import com.gayuh.SpringValidation.data.Person;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    void validatePerson() {
        Person person = new Person("", "");

        Set<ConstraintViolation<Person>> constraintValidations = validator.validate(person);
        System.out.println(constraintValidations.isEmpty());
    }
}
