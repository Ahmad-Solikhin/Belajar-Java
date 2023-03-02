package com.gayuh.javalogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFormat {

    private static final Logger log = LoggerFactory.getLogger(LogFormat.class);

    public static void main(String[] args) {
        log.info("Without parameter");
        log.info("Numbers : {}", 10);
        log.error("ups", new NullPointerException("Ada error nih"));
    }

}
