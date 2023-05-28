package com.gayuh.webmvc;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .param("firstName", "Ahmad")
                        .param("middleName", "Solikhin Gayuh")
                        .param("lastName", "Raharjo")
                        .param("email", "ahmadsgr39@gmail.com")
                        .param("phone", "087873777429")
                        .param("address.country", "Indonesia")
                        .param("hobbies[0]", "Turu")
                        .param("hobbies[1]", "Rebahan")
                        .param("socialMedias[0].name", "Instagram")
                        .param("socialMedias[0].location", "asgr_397")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("address in country Indonesia"))
        );
    }

    @Test
    void createPersonFailed() throws Exception {
        mockMvc.perform(
                post("/person")
                        .param("firstName", "Ahmad")
                        .param("middleName", "Solikhin Gayuh")
                        .param("lastName", "Raharjo")
                        .param("email", "ahmadsgr39@gmail.com")
                        .param("phone", "087873777429")
                        .param("hobbies[0]", "Turu")
                        .param("hobbies[1]", "Rebahan")
                        .param("socialMedias[0].name", "Instagram")
                        .param("socialMedias[0].location", "asgr_397")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Invalid Data"))
        );
    }
}
