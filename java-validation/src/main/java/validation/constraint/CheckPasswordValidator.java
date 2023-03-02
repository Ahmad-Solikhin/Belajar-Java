package validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import validation.Register;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Register> {

    @Override
    public boolean isValid(Register register, ConstraintValidatorContext constraintValidatorContext) {
        if (register.getPassword() == null || register.getRetypePassword() == null){
            return true;
        }

        return register.getPassword().equals(register.getRetypePassword());
    }
}
