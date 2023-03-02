package validation;

import org.junit.jupiter.api.Test;
import validation.service.UserService;

import java.lang.reflect.Method;

public class CrossParameterTest extends AbstractValidatorTest{

    @Test
    void test() throws NoSuchMethodException {
        UserService userService = new UserService();

        Method method = UserService.class.getMethod("register", String.class, String.class, String.class);

        String username = "Gayuh";
        String password = "rahasia";
        String retypePassword = "rahasia";

        var violation = executableValidator.validateParameters(userService, method, new Object[]{
                username, password, retypePassword
        });

        violation.forEach(v -> {
            System.out.println(v.getMessage());
            System.out.println(v.getPropertyPath());
        });
    }
}
