package com.gayuh.spring.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@SpringBootTest(classes = ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {
    @Autowired
    private TestApplication.SampleResource sampleResource;

    @Test
    void testResourceLoader() throws Exception {
        System.out.println(sampleResource.getText().trim());

        Assertions.assertEquals("Ahmad Solikhin Gayuh Raharjo", sampleResource.getText());
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        public static class SampleResource implements ResourceLoaderAware{
            @Setter
            private ResourceLoader resourceLoader;

            public String getText() throws Exception{
                Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
                try (InputStream inputStream = resource.getInputStream()){
                    return new String(inputStream.readAllBytes());
                }
            }

        }

    }

}
