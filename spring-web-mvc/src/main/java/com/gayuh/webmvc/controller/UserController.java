package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserController {

    @GetMapping("/user/current")
    private ResponseEntity<String> getUser(@SessionAttribute(name = "user")User user){
        return ResponseEntity.ok("Hello " + user.getUsername());
    }

}
