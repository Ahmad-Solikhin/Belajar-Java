package com.gayuh.restfulapi.service.user;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.RegisterUserRequest;
import com.gayuh.restfulapi.model.UpdateUserRequest;
import com.gayuh.restfulapi.model.UserResponse;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    public void register(RegisterUserRequest request);

    public UserResponse get(User user);
    @Transactional
    public UserResponse update(User user, UpdateUserRequest request);

}
