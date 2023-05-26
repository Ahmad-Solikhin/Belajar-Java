package com.gayuh.SpringValidation.data;

import com.gayuh.SpringValidation.validation.Palindrome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foo {
    @Palindrome
    private String name;
}
