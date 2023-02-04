package spring.starter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.starter.dto.userDetails.UserDetailsResponse;
import spring.starter.service.AppUserService;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final AppUserService appUserService;
    @GetMapping
    public ResponseEntity<UserDetailsResponse> findUserDetails(){
        return ResponseEntity.ok().body(appUserService.findUserDetails());
    }

}
