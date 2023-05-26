package com.gayuh.spring.config.profileenvironment;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest(classes = ProfileEnvironmentTest.TestApplication.class)
@ActiveProfiles({"local"})
public class ProfileEnvironmentTest {

    @Autowired
    private TestApplication.SampleProfile sampleProfile;

    @Test
    void testActiveProfiles() {
        System.out.println(Arrays.toString(sampleProfile.getProfiles()));
    }

    @SpringBootApplication
    public static class TestApplication{
        @Component
        public static class SampleProfile implements EnvironmentAware {

            @Setter
            private Environment environment;

            public String[] getProfiles(){
                return environment.getActiveProfiles();
            }
        }
    }
}
