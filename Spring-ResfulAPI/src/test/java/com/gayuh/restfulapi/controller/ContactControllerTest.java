package com.gayuh.restfulapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayuh.restfulapi.entity.Contact;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.ContactResponse;
import com.gayuh.restfulapi.model.CreateContactRequest;
import com.gayuh.restfulapi.model.UpdateContactRequest;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.repository.ContactRepository;
import com.gayuh.restfulapi.repository.UserRepository;
import com.gayuh.restfulapi.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();

        user = new User();
        user.setUsername("asgr39");
        user.setName("Gayuh");
        user.setToken("test");
        user.setTokenExpireAt(System.currentTimeMillis() + (1_000L * 60));
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));

        userRepository.save(user);
    }

    @Test
    void createSuccess() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setPhone("07201970");
        request.setEmail("ah@gmail.com");
        request.setFirstName("Gayuh");
        request.setLastName("Raharjo");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isCreated()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(request.getPhone(), response.getData().getPhone());
            assertNotNull(response.getData().getId());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void createInvalid() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setPhone("07201970");
        request.setEmail("ahgmail.com");
        request.setFirstName("");
        request.setLastName("Raharjo");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getErrors());
            System.out.println(response.getErrors());
        });
    }

    @Test
    void getContactNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/saya")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getErrors());
            System.out.println(response.getErrors());
        });
    }

    @Test
    void getContactSuccess() throws Exception {
        Contact contact = new Contact();
        contact.setPhone("07201970");
        contact.setEmail("ah@gmail.com");
        contact.setFirstName("Gayuh");
        contact.setLastName("Raharjo");
        contact.setUser(this.user);

        contactRepository.save(contact);

        mockMvc.perform(
                get("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(contact.getPhone(), response.getData().getPhone());
            assertNotNull(response.getData().getId());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void updateContactBadRequest() throws Exception {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setPhone("07201970");
        request.setEmail("ahgmail.com");
        request.setFirstName("");
        request.setLastName("Raharjo");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/contacts/hsga")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getErrors());
            System.out.println(response.getErrors());
        });
    }

    @Test
    void updateContactSuccess() throws Exception {
        Contact contact = new Contact();
        contact.setPhone("07201970");
        contact.setEmail("ah@gmail.com");
        contact.setFirstName("Gayuh");
        contact.setLastName("Raharjo");
        contact.setUser(this.user);
        contactRepository.save(contact);

        UpdateContactRequest request = new UpdateContactRequest();
        request.setPhone(contact.getPhone());
        request.setEmail(contact.getEmail());
        request.setFirstName("Ahmad");
        request.setLastName(contact.getLastName());

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(request.getPhone(), response.getData().getPhone());
            assertEquals(request.getFirstName(), response.getData().getFirstName());
            assertNotNull(response.getData().getId());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteContactNotFound() throws Exception {

        mockMvc.perform(
                delete("/api/contacts/saya")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<ContactResponse>>() {
            });

            assertNotNull(response.getErrors());
            System.out.println(response.getErrors());
        });
    }

    @Test
    void deleteContactSuccess() throws Exception {
        Contact contact = new Contact();
        contact.setPhone("07201970");
        contact.setEmail("ah@gmail.com");
        contact.setFirstName("Gayuh");
        contact.setLastName("Raharjo");
        contact.setUser(this.user);

        contactRepository.save(contact);

        mockMvc.perform(
                delete("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals("Oke", response.getData());

            assertFalse(contactRepository.existsById(contact.getId()));
        });
    }

    @Test
    void searchNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(0, response.getData().size());
            assertEquals(0, response.getPaging().getTotalPage());
            assertEquals(0, response.getPaging().getCurrentPage());
            assertEquals(10, response.getPaging().getSize());
        });
    }

    @Test
    void searchSuccessUsingName() throws Exception {

        for (int i = 0; i < 20; i++) {
            Contact contact = new Contact();
            contact.setPhone("07201970");
            contact.setEmail("ah@gmail.com");
            contact.setFirstName("Gayuh" + i);
            contact.setLastName("Raharjo");
            contact.setUser(this.user);
            contactRepository.save(contact);
        }

        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "Raharjo")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(10, response.getData().size());
            assertEquals(2, response.getPaging().getTotalPage());
            assertEquals(0, response.getPaging().getCurrentPage());
            assertEquals(10, response.getPaging().getSize());
        });
    }

    @Test
    void searchSuccessUsingEmail() throws Exception {

        for (int i = 0; i < 20; i++) {
            Contact contact = new Contact();
            contact.setPhone("07201970");
            contact.setEmail("ah@gmail.com");
            contact.setFirstName("Gayuh" + i);
            contact.setLastName("Raharjo");
            contact.setUser(this.user);
            contactRepository.save(contact);
        }

        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "Raharjo")
                        .queryParam("email", ".com")
                        .queryParam("size", "2")
                        .queryParam("phone", "201")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertNull(response.getErrors());
            assertEquals(2, response.getData().size());
            assertEquals(10, response.getPaging().getTotalPage());
            assertEquals(0, response.getPaging().getCurrentPage());
            assertEquals(2, response.getPaging().getSize());
        });
    }

    @Test
    void testDefaultDeleteAll() {

    }
}
