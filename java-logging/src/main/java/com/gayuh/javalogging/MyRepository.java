package com.gayuh.javalogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRepository {

    private static Logger log = LoggerFactory.getLogger(MyRepository.class);

    public void save(){
        log.info("repository save");
    }

}
