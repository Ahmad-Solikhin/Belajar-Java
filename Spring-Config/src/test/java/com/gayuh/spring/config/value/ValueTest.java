package com.gayuh.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {
    @Autowired
    private TestApplication.SystemProperties systemProperties;
    @Autowired
    private TestApplication.ApplicationProperties properties;

    @Test
    void testValue() {
        System.out.println(properties.getName());
        System.out.println(properties.getVersion());
        System.out.println(properties.getProductionMode());
    }

    @Test
    void testSystemProperties() {
        System.out.println(systemProperties.getJavaHome());
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        @Getter
        public static class SystemProperties{
            @Value("${JAVA_HOME}")
            private String javaHome;

        }
        @Component
        @Getter
        public static class ApplicationProperties{

            @Value("${application.name}")
            private String name;
            @Value("${application.version}")
            private Integer version;
            @Value("${application.production-mode}")
            private Boolean productionMode;

        }
    }
}
