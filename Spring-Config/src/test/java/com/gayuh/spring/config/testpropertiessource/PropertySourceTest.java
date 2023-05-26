package com.gayuh.spring.config.testpropertiessource;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@TestPropertySources({
        @TestPropertySource("classpath:/test.properties")
})
@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {

    @Autowired
    private TestApplication.SampleProperties properties;

    @Test
    void testPropertiesSource() {
        System.out.println(properties.getName());
        System.out.println(properties.getVersion());
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        @Getter
        public static class SampleProperties{
            @Value("${sample.name}")
            private String name;
            @Value("${sample.version}")
            private Integer version;
        }

    }
}
