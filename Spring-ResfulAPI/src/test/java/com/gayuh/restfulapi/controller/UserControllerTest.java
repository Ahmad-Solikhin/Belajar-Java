package com.gayuh.restfulapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.RegisterUserRequest;
import com.gayuh.restfulapi.model.UserResponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("asgr39");
        request.setPassword("rahasia");
        request.setName("Gayuh");

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            Assertions.assertEquals("Oke", response.getData());
        });

        User user = userRepository.findById("asgr39").orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Gayuh", user.getName());
    }

    @Test
    void testRegisterFailed() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest("", "", "");

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {

            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            Assertions.assertNotNull(response.getErrors());
            System.out.println(response.getErrors());

        });

    }

    @Test
    void testDuplicate() throws Exception {
        User user = new User();
        user.setUsername("asgr39");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("Gayuh");

        userRepository.save(user);

        RegisterUserRequest request = new RegisterUserRequest("asgr39", "rahasia", "Gayuh");

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {

            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            Assertions.assertNotNull(response.getErrors());
            Assertions.assertEquals("Username already taken", response.getErrors());

        });
    }

    @Test
    void getUserUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "Not There")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<UserResponse>>() {
            });
            Assertions.assertNotNull(response.getErrors());
            Assertions.assertEquals("Unauthorized", response.getErrors());
        });
    }

    @Test
    void getUserUnauthorizedTokenNotSend() throws Exception {
        mockMvc.perform(
                get("/api/users/current")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<UserResponse>>() {
            });
            Assertions.assertNotNull(response.getErrors());
            Assertions.assertEquals("Unauthorized", response.getErrors());
        });
    }

    @Test
    void getUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setToken("test");
        user.setName("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setTokenExpireAt(System.currentTimeMillis() + (1000 * 60 * 10));
        userRepository.save(user);

        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<UserResponse>>() {
            });
            Assertions.assertNull(response.getErrors());
            Assertions.assertEquals("test", response.getData().getUsername());
        });
    }
}
