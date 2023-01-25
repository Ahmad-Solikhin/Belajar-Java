package spring.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.starter.service.GreetingService;

@RestController
public class HelloSource {

    //Bisa pake autowired kayak gini
    @Autowired
    private GreetingService greetingService;

    //Atau kayak gini
//    public HelloSource(GreetingService greetingService) {
//        this.greetingService = greetingService;
//    }

    @GetMapping("/hello")
    public String helloWord(){
        return greetingService.sayGreeting();
    }

}
