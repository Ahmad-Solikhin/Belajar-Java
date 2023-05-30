package com.gayuh.restfulapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {

    @NotBlank
    @Length(max = 100)
    private String username;
    @NotBlank
    @Length(max = 100)
    private String password;
    @NotBlank
    @Length(max = 100)
    private String name;

}
