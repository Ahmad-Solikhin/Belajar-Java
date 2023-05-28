package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if ("Gayuh".equals(username) && "rahasia".equals(password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", new User(username));

            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok().body("OK");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("KO");
        }
    }

    @GetMapping("/auth/user")
    public ResponseEntity<String> getUser(@CookieValue(name = "username") String username){
        return  ResponseEntity.ok("Hello " + username);
    }

}
