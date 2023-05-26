package com.gayuh.SpringValidation;

import com.gayuh.SpringValidation.data.Foo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class PalindromeTest {

    @Autowired
    private Validator validator;

    @Test
    void palindromeValid() {
        Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("Kodok"));
        Assertions.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void palindromeInvalid() {
        Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("Gayuh"));
        Assertions.assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void testPalindromeMessage() {
        Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("Gayuh"));
        Assertions.assertFalse(constraintViolations.isEmpty());

        System.out.println(constraintViolations.stream().findFirst().get().getMessage());
    }
}
