package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonController {

    @PostMapping("/person")
    public ResponseEntity<String> createPerson(
            @ModelAttribute @Valid CreatePersonRequest request,
            BindingResult bindingResult
            ){

        List<ObjectError> errors = bindingResult.getAllErrors();
        if (!errors.isEmpty()){

            errors.forEach(objectError ->{
                System.out.println(objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body("Invalid Data");
        }

        System.out.println(request);

        String response = new StringBuilder().append("Success create Person ")
                .append(request.getFirstName()).append(" ").append(request.getMiddleName()).append(" ").append(request.getLastName())
                .append(", phone ").append(request.getPhone()).append(", address in country ").append(request.getAddress().getCountry())
                .toString();
        return ResponseEntity.ok(response);

    }

}
