package com.gayuh.SpringValidation;

import com.gayuh.SpringValidation.helper.SayHello;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SayHelloTest {

    @Autowired
    private SayHello sayHello;

    @Test
    void success() {
        String message = sayHello.sayHello("Gayuh");

        Assertions.assertEquals("Hello Gayuh", message);
    }

    @Test
    void error() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                sayHello.sayHello("")
        );

    }
}
