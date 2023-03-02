package validation;

import org.junit.jupiter.api.Test;
import validation.group.CreditCardPaymentGroup;

public class GroupConvertTest extends AbstractValidatorTest{

    @Test
    void test(){
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(100_000L);
        payment.setCreditCard("4111111111111111");
        payment.setEmail("ahmadsgr39@gmail.com");
        payment.setCustomer(new Customer());

        var violation = validator.validate(payment, CreditCardPaymentGroup.class);
        violation.forEach(a -> {
            System.out.println("Message: " + a.getMessage());
            System.out.println("Object/Bean: " + a.getLeafBean());
            System.out.println("Constraint: " + a.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value Field: " + a.getInvalidValue());
            a.getPropertyPath().forEach(v -> System.out.println("Path: " + v.getName()));
            System.out.println();
        });
    }
}
