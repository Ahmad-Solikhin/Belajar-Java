package com.gayuh.spring.config.appproperties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = ApplicationPropertiesTest.TestApplication.class)
public class ApplicationPropertiesTest {
    @Autowired
    private Environment environment;

    @Test
    void testApplicationProperties() {
        String applicationName = environment.getProperty("application.name");
        System.out.println(applicationName);
    }

    @SpringBootApplication
    public static class TestApplication{

    }
}
