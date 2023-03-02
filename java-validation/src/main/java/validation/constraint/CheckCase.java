package validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validation.enums.CaseMode;

import java.lang.annotation.Documented;

import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = {CheckCaseValidator.class})
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface CheckCase {

    CaseMode mode();

    String message() default "invalid case format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
