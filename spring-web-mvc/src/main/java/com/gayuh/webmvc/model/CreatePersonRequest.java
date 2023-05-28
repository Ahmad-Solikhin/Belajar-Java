package com.gayuh.webmvc.model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    @Valid
    @NotNull
    private CreateAddressRequest address;
    @NotBlank
    private String firstName;
    private String middleName;
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    private List<String> hobbies;
    private List<CreateSocialMediaRequest> socialMedias;

}
