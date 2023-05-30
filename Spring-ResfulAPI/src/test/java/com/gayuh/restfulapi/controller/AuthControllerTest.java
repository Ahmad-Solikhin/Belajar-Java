package com.gayuh.restfulapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.LoginUserRequest;
import com.gayuh.restfulapi.model.TokenResponse;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.repository.UserRepository;
import com.gayuh.restfulapi.security.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void loginSuccess() throws Exception {
        User user = new User();
        user.setName("Gayuh");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setUsername("asgr39");

        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest("asgr39", "rahasia");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<TokenResponse>>() {
            });

            Assertions.assertNotNull(response.getData().getToken());
            Assertions.assertNotNull(response.getData().getExpiredAt());

            User userDb = userRepository.findById(request.getUsername()).orElse(null);
            Assertions.assertNotNull(userDb);
            Assertions.assertEquals(response.getData().getToken(), userDb.getToken());
        });

    }

    @Test
    void loginUserNotFound() throws Exception {
        User user = new User();
        user.setName("Gayuh");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setUsername("asgr39");

        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest("test", "test");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<TokenResponse>>() {
            });

            Assertions.assertNotNull(response.getErrors());
            Assertions.assertEquals("Username or Password Wrong", response.getErrors());
        });
    }

    @Test
    void loginFailedWrongPassword() throws Exception {
        User user = new User();
        user.setName("Gayuh");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setUsername("asgr39");

        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest("asgr39", "salah");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<TokenResponse>>() {
            });

            Assertions.assertNotNull(response.getErrors());
            Assertions.assertEquals("Username or Password Wrong", response.getErrors());
        });
    }
}
