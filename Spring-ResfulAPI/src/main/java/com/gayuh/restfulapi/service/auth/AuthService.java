package com.gayuh.restfulapi.service.auth;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.LoginUserRequest;
import com.gayuh.restfulapi.model.TokenResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    public TokenResponse login(LoginUserRequest request);

    @Transactional
    public void logout(User user);
}