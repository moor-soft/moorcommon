package moorcommon.annotation.impl;

import moorcommon.annotation.MoorPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoorPasswordImpl implements
        ConstraintValidator<MoorPassword, String> {

    @Override
    public void initialize(MoorPassword contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        Boolean result = false;
        /*
        Minimum eight characters, at least one letter and one number:
        "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"

        Minimum eight characters, at least one letter, one number and one special character:
        "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"

        Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"

        Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"

        Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$"
                */

        if (contactField != null && contactField.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            result = true;
        }

        return result;
    }

}