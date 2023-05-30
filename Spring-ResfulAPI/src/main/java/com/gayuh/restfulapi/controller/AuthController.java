package com.gayuh.restfulapi.controller;

import com.gayuh.restfulapi.model.LoginUserRequest;
import com.gayuh.restfulapi.model.TokenResponse;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping(
            value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<TokenResponse>> login(@RequestBody LoginUserRequest request) {
        TokenResponse response = authService.login(request);

        return ResponseEntity.ok(WebResponse
                .<TokenResponse>builder()
                .data(response)
                .build());
    }

}
