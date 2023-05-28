package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.model.ErrorResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {


    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        ErrorResponse response = new ErrorResponse(status, message);

        return ResponseEntity.status(status).body(response);
    }
}
