package validation;

import org.hibernate.validator.constraints.Email;
import org.junit.jupiter.api.Test;
import validation.group.CreditCardPaymentGroup;
import validation.payload.EmailErrorPayload;

public class PayloadTest extends AbstractValidatorTest{

    @Test
    void test(){
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setEmail("ahmadsgr39@gmail.com");
        payment.setAmount(100_000L);
        payment.setCreditCard("76D");

        var violation = validator.validate(payment, CreditCardPaymentGroup.class);

        violation.forEach(a -> {
            a.getConstraintDescriptor().getPayload().forEach(payload -> {
                if (payload == EmailErrorPayload.class) {
                    EmailErrorPayload emailErrorPayload = new EmailErrorPayload();
                    emailErrorPayload.sendEmail(a);
                }
            });
        });
    }
}
