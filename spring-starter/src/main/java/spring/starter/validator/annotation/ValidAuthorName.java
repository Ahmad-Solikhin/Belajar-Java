package spring.starter.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.starter.validator.AuthorNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AuthorNameValidator.class)
public @interface ValidAuthorName {

    //Message error yang ditampilkan
    String message() default "Invalid Author Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
