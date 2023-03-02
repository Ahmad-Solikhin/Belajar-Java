package com.gayuh.javalogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLogger {

    private static final Logger log = LoggerFactory.getLogger(HelloLogger.class);

    public static void main(String[] args) {
        log.info("Hallo Guys");
        sayHello("Gayuh");
        sayHello("Embul");
    }

    public static void sayHello(String name){
        log.info("Say Hello for {} Executed", name);
    }

}
