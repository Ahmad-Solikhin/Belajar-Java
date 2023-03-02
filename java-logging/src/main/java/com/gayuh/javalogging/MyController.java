package com.gayuh.javalogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyController {

    private static Logger log = LoggerFactory.getLogger(MyController.class);

    private MyService myService;

    public MyController(MyService service){
        myService = service;
    }

    public void save(){
        log.info("controller save");
        myService.save();
    }
}
