package validation;

import org.junit.jupiter.api.Test;
import validation.group.CreditCardPaymentGroup;

public class ConstraintCompositionTest extends AbstractValidatorTest{

    @Test
    void test(){
        Payment payment = new Payment();

        var violation = validator.validate(payment, CreditCardPaymentGroup.class);
        violation.forEach(v -> {
            System.out.println(v.getMessage());
            System.out.println(v.getInvalidValue());
            System.out.println("=================");
        });
    }
}
