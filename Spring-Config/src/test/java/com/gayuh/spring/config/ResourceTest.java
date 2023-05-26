package com.gayuh.spring.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ResourceTest {

    @Test
    void testResource() throws IOException {
        ClassPathResource resource = new ClassPathResource("/application.properties");

        Assertions.assertNotNull(resource);

        Assertions.assertTrue(resource.exists());
        System.out.println(resource.getFile().getAbsolutePath());
    }
}
