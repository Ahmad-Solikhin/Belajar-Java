package com.gayuh.springAOP.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void helloService() {
        Assertions.assertEquals("Hello Gayuh", helloService.hello("Gayuh"));
        Assertions.assertEquals("Bye Gayuh", helloService.bye("Gayuh"));
        helloService.test();
    }
}
