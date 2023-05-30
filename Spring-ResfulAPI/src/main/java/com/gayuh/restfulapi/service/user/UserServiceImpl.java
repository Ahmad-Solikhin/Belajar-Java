package com.gayuh.restfulapi.service.user;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.RegisterUserRequest;
import com.gayuh.restfulapi.model.UserResponse;
import com.gayuh.restfulapi.repository.UserRepository;
import com.gayuh.restfulapi.security.BCrypt;
import com.gayuh.restfulapi.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ValidationService validationService;

    @Override
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    @Override
    public UserResponse get(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
