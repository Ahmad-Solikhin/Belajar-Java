package com.gayuh.restfulapi.controller;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.RegisterUserRequest;
import com.gayuh.restfulapi.model.UserResponse;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController()
@Slf4j
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> register(@RequestBody RegisterUserRequest request){
        userService.register(request);

        log.info("Create User with username {}", request.getUsername());

        return ResponseEntity.ok(WebResponse
                .<String>builder()
                .data("Oke")
                .build()
        );
    }

    @GetMapping(
            value = "current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<UserResponse>> get(User user){
        UserResponse response = userService.get(user);

        return ResponseEntity.ok(WebResponse
                .<UserResponse>builder()
                .data(response)
                .build());
    }

}
