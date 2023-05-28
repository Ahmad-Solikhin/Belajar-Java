package com.gayuh.webmvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body("Validation Error : " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException exception){
        return ResponseEntity.badRequest().body("Validation Error : " + exception.getMessage());
    }
}
