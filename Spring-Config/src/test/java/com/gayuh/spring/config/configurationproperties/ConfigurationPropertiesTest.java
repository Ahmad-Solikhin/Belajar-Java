package com.gayuh.spring.config.configurationproperties;

import com.gayuh.spring.config.converter.StringToDateConverter;
import com.gayuh.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.time.Duration;
import java.util.Date;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {
    @Autowired
    private ApplicationProperties properties;
    @Autowired
    private ConversionService conversionService;

    @Test
    void testConfigurationProperties() {
        System.out.println(properties.getName());
        System.out.println(properties.getVersion());
        System.out.println(properties.isProductionMode());
    }

    @Test
    void testDatabaseProperties() {
        System.out.println(properties.getDatabase().getDatabase());
        System.out.println(properties.getDatabase().getUsername());
        System.out.println(properties.getDatabase().getPassword());
        System.out.println(properties.getDatabase().getUrl());
    }

    @Test
    void testCollections() {
        System.out.println(properties.getDatabase().getWhiteListTables());
        System.out.println(properties.getDatabase().getMaxTableSize());
    }

    @Test
    void testEmbeddedCollection() {
        properties.getRoles().forEach((k, v) -> {
            System.out.print(k + " = ");
            System.out.println(v.getId() + ", " + v.getName());
        });
        System.out.println(properties.getDefaultRoles());
    }

    @Test
    void testDuration() {
        System.out.println(properties.getDefaultTimeout());
    }

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));
    }

    @Test
    void testCustomConverter() {
        Date expireDate = properties.getExpireDate();

        System.out.println(expireDate.toString());

    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    @Import(StringToDateConverter.class)
    public static class TestApplication{

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter){
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }
}
