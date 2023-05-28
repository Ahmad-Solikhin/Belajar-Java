package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello")
    public void helloWorld(HttpServletResponse response,
                           @RequestParam(name = "name", required = false) String name) throws IOException{

        String responseBody = helloService.hello(name);

        response.getWriter().println(responseBody);
    }

    @GetMapping("/web/hello")
    public ModelAndView hello(@RequestParam(name = "name", required = false) String name){

        if (name == null){
            return new ModelAndView("redirect:/web/hello?name=Guest");
        }

        return new ModelAndView("hello", Map.of(
                "title", "Belajar Mustache",
                "name", name
        ));
    }



}
