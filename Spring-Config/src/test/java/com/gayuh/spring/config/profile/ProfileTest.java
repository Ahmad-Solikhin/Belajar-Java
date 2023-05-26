package com.gayuh.spring.config.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfileTest.TestApplication.class)
@ActiveProfiles({"local"}) //Digunakan untuk mengubah active profile
public class ProfileTest {

    @Autowired
    private TestApplication.SayHello sayHello;

    @Test
    void testProfile() {
        System.out.println(sayHello.say("Gayuh"));
    }

    @SpringBootApplication
    public static class TestApplication{

        public interface SayHello{
            String say(String name);
        }

        @Component
        @Profile({"local"})
        public static class SayHelloLocal implements SayHello{

            @Override
            public String say(String name) {
                return "Hello " + name + " from local";
            }
        }

        @Component
        @Profile({"production"})
        public static class SayHelloProduction implements SayHello{

            @Override
            public String say(String name) {
                return "Hello " + name + " from production";
            }
        }
    }
}
