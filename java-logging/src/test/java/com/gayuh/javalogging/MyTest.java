package com.gayuh.javalogging;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.UUID;

public class MyTest {

    @Test
    void test(){
        String requestId = UUID.randomUUID().toString();

        MyService myService = new MyService(new MyRepository());
        MyController myController = new MyController(myService);

        MDC.put("requestId", requestId);
        myController.save();
        MDC.remove("requestId");

    }
}
