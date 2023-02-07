package spring.starter.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import spring.starter.validator.annotation.ValidAuthorName;

//Untuk mengaktifkan validator
@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName, String> {
    @Override
    public boolean isValid(String authorName, ConstraintValidatorContext constraintValidatorContext) {
        return !authorName.equalsIgnoreCase("gayuh");
    }
}
