package com.gayuh.restfulapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayuh.restfulapi.entity.Address;
import com.gayuh.restfulapi.entity.Contact;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.AddressResponse;
import com.gayuh.restfulapi.model.CreateAddressRequest;
import com.gayuh.restfulapi.model.UpdateAddressRequest;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.repository.AddressRepository;
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
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Contact contact;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        user = new User();
        user.setUsername("asgr39");
        user.setName("Gayuh");
        user.setToken("test");
        user.setTokenExpireAt(System.currentTimeMillis() + (1_000L * 60));
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));

        userRepository.save(user);

        contact = new Contact();
        contact.setPhone("07201970");
        contact.setEmail("ah@gmail.com");
        contact.setFirstName("Gayuh");
        contact.setLastName("Raharjo");
        contact.setUser(this.user);

        contactRepository.save(contact);
    }

    @Test
    void createAddressBadRequest() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setContactId(contact.getId());
        request.setCountry(" ");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/contacts/" + contact.getId() + "/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createAddressSuccess() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setContactId(contact.getId());
        request.setCountry("Indonesia");
        request.setCity("Bekasi");
        request.setStreet("Villa");
        request.setProvince("Jawa Barat");
        request.setContactId("17520");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/contacts/" + contact.getId() + "/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData());
            assertEquals(response.getData().getCountry(), request.getCountry());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts/test/addresses/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNotNull(response.getErrors());
            assertNull(response.getData());
        });
    }

    @Test
    void getAddressSuccess() throws Exception {
        Address address = new Address();
        address.setContact(this.contact);
        address.setPostalCode("17520");
        address.setCountry("Indonesia");
        address.setCity("Bekasi");
        address.setStreet("Vill");
        address.setProvince("Jawa Barat");
        addressRepository.save(address);

        mockMvc.perform(
                get("/api/contacts/" + this.contact.getId() +  "/addresses/" + address.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", this.user.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData());
            assertEquals(response.getData().getId(), address.getId());
            assertEquals(response.getData().getCountry(), address.getCountry());
        });
    }

    @Test
    void updateAddressBadRequest() throws Exception {
        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setContactId(contact.getId());
        request.setCountry(" ");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/contacts/" + contact.getId() + "/addresses/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateAddressSuccess() throws Exception {
        Address address = new Address();
        address.setContact(this.contact);
        address.setPostalCode("Lama");
        address.setCountry("Lama");
        address.setCity("Lama");
        address.setStreet("Lama");
        address.setProvince("Lama");
        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setContactId(contact.getId());
        request.setCountry("Indonesia");
        request.setCity("Bekasi");
        request.setStreet("Villa");
        request.setProvince("Jawa Barat");
        request.setPostalCode("17520");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/contacts/" + contact.getId() + "/addresses/" + address.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData());
            assertEquals(response.getData().getCountry(), request.getCountry());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteAddressNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/contacts/" + contact.getId() + "/addresses/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getData());
            assertNotNull(response.getErrors());
            assertEquals("Address Not Found", response.getErrors().trim());
        });
    }

    @Test
    void deleteAddressSuccess() throws Exception {
        Address address = new Address();
        address.setCountry("Indonesia");
        address.setCity("Bekasi");
        address.setStreet("Villa");
        address.setProvince("Jawa Barat");
        address.setPostalCode("17520");
        address.setContact(this.contact);
        addressRepository.save(address);

        mockMvc.perform(
                delete("/api/contacts/" + contact.getId() + "/addresses/" + address.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData());
            assertEquals(response.getData(), "Oke");

            assertFalse(addressRepository.existsById(address.getId()));
        });
    }

    @Test
    void getAddressesNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts/gada/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", this.user.getToken())
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
            assertNull(response.getData());
            assertEquals("Contact Not Found", response.getErrors());
        });
    }

    @Test
    void getAddressesSuccess() throws Exception {
        Address address;
        for (int i = 0; i < 20; i++) {
            address = new Address();
            address.setCountry("Indonesia" + i);
            address.setCity("Bekasi");
            address.setStreet("Villa");
            address.setProvince("Jawa Barat");
            address.setPostalCode("17520");
            address.setContact(this.contact);
            addressRepository.save(address);
        }

        mockMvc.perform(
                get("/api/contacts/" + contact.getId() + "/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", this.user.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData());
            assertEquals(20, response.getData().size());
        });
    }

    @Test
    void testDefaultDeleteData() {

    }
}
