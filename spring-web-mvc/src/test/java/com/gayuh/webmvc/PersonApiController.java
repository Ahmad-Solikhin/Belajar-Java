package com.gayuh.webmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayuh.webmvc.model.CreateAddressRequest;
import com.gayuh.webmvc.model.CreatePersonRequest;
import com.gayuh.webmvc.model.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPrrson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Ahmad");
        request.setMiddleName("Solikhin Gayuh");
        request.setLastName("Raharjo");
        request.setEmail("ahmadsgr@gmail.com");
        request.setPhone("087873777281");
        request.setHobbies(List.of("Turu", "Rebahan"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "gayuh229"));
        request.setAddress(new CreateAddressRequest("Villa", "Indonesia", "Bekasi", "17520"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonBadRequest() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Ahmad");
        request.setMiddleName("Solikhin Gayuh");
        request.setLastName("Raharjo");
        request.setEmail("ahmadsgr@gmail.com");
        request.setPhone("087873777281");
        request.setHobbies(List.of("Turu", "Rebahan"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "gayuh229"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation Error"))
        );
    }
}
