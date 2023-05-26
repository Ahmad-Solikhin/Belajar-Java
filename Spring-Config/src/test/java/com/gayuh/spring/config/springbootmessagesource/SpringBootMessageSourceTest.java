package com.gayuh.spring.config.springbootmessagesource;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;
@SpringBootTest(classes = SpringBootMessageSourceTest.TestApplication.class)
public class SpringBootMessageSourceTest {
    @Autowired
    private TestApplication.SampleSource sampleSource;

    @Test
    void testHello() {
        System.out.println(sampleSource.hello(Locale.ENGLISH));
        System.out.println(sampleSource.hello(new Locale("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication{
        @Component
        public static class SampleSource implements MessageSourceAware {
            @Setter
            private MessageSource messageSource;

            public String hello(Locale locale){
                return messageSource.getMessage("hello", new Object[]{"Gayuh"}, locale);
            }
        }
    }
}
