package validation;

import org.junit.jupiter.api.Test;
import validation.group.PaymentGroup;

public class GroupSequenceTest extends AbstractValidatorTest{

    @Test
    void testGroupSequence(){
        Payment payment = new Payment();

        var violation = validator.validate(payment, PaymentGroup.class);

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
