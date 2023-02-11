package spring.starter.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.starter.dto.HelloMessageResponse;
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
    public ResponseEntity<HelloMessageResponse> helloWord(){
        HelloMessageResponse response = new HelloMessageResponse();
        response.setMessage(greetingService.sayGreeting());
        return ResponseEntity.accepted().body(response);
    }
}
