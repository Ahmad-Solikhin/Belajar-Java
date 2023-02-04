package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.starter.dto.userDetails.UserDetailsResponse;
import spring.starter.exception.NotFoundException;
import spring.starter.repository.AppUserRepository;
import spring.starter.service.AppUserService;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Invalid Username or Password"));
    }

    @Override
    public UserDetailsResponse findUserDetails() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserDetailsResponse response = new UserDetailsResponse();
        response.setUsername(ctx.getAuthentication().getName());
        return response;
    }
}
