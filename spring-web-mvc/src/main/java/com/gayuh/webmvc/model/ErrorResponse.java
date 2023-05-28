package com.gayuh.webmvc.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class ErrorResponse {

    private Integer statusCode;
    private String errorMessage;

}
