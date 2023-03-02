package validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.group.CreditCardPaymentGroup;

public class HibernateValidatorTest extends AbstractValidatorTest {

    @Test
    void hibernateTest(){
        Payment payment = new Payment();
        payment.setEmail("ahmadSgr39gmail.com");
        payment.setCreditCard("08137193");

        var violation = validator.validate(payment, CreditCardPaymentGroup.class);

        violation.forEach(a -> {
            System.out.println(a.getMessage());
            System.out.println(a.getInvalidValue());
            System.out.println(a.getPropertyPath());
            System.out.println();
        });
    }
}
