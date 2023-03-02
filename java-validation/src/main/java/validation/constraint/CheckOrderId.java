package validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import validation.enums.CaseMode;
import validation.group.CreditCardPaymentGroup;
import validation.group.MobileBankingPaymentGroup;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@CheckCase(groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class},
        mode = CaseMode.UPPER, message = "order must upper case")
@NotBlank(message = "Order id can not balank",
        groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
@Documented
@Constraint(validatedBy = {}) //Tidak dibutuhkan lagi
@Target({FIELD})
@Retention(RUNTIME)
@ReportAsSingleViolation//Kalo pake ini nanti message yang dipake adalah message yang dibawah
public @interface CheckOrderId {

    String message() default "invalid order id"; //Ini formalitas aja

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
