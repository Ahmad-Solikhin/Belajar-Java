package com.gayuh.restfulapi.service.user;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.RegisterUserRequest;
import com.gayuh.restfulapi.model.UserResponse;

public interface UserService {

    public void register(RegisterUserRequest request);

    public UserResponse get(User user);
}
