package com.gayuh.restfulapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateContactRequest {
    @NotBlank
    @JsonIgnore
    private String id;
    @NotBlank
    @Length(max = 100)
    private String firstName;
    @Size(max = 100)
    private String lastName;
    @Email
    @Size(max = 100)
    private String email;
    @Size(max = 100)
    private String phone;

}
