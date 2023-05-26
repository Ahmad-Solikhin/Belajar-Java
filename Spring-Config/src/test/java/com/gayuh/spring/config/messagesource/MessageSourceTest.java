package com.gayuh.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    private ApplicationContext applicationContext;

    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    void testDefaultLocal() {
        String message = messageSource.getMessage("hello", new Object[]{"Gayuh"}, Locale.ENGLISH);
        Assertions.assertEquals(message, "Hello Gayuh");
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Gayuh"}, new Locale("in_ID"));
        Assertions.assertEquals(message, "Halo Gayuh");
    }

    @SpringBootApplication
    public static class TestApplication{

        @Bean
        public MessageSource messageSource(){
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("my");
            return messageSource;
        }
    }
}
