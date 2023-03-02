package com.gayuh.javalogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {

    private static Logger log = LoggerFactory.getLogger(MyService.class);

    private MyRepository myRepository;

    public MyService(MyRepository repository){
        myRepository = repository;
    }

    public void save(){
        log.info("service save");
        myRepository.save();
    }

}
