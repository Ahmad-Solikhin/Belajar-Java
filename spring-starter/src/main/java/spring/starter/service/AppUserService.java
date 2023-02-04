package spring.starter.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import spring.starter.dto.userDetails.UserDetailsResponse;

public interface AppUserService extends UserDetailsService {

    //Method untuk melihat detail user yang sudah login
    public UserDetailsResponse findUserDetails();

}
