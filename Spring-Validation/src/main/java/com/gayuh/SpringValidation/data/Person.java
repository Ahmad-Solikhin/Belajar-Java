package com.gayuh.SpringValidation.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
}
