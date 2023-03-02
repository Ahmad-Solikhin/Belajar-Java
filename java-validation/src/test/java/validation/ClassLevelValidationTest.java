package validation;

import org.junit.jupiter.api.Test;
import validation.group.CreditCardPaymentGroup;

public class ClassLevelValidationTest extends AbstractValidatorTest{

    @Test
    void test(){
        Register register = new Register();
        register.setUsername("gayuh");
        register.setPassword("rahasia");
        register.setRetypePassword("rahasia123");

        var violation = validator.validate(register);
        violation.forEach(v -> {
            System.out.println(v.getMessage());
            System.out.println(v.getInvalidValue());
            System.out.println("=================");
        });
    }
}
