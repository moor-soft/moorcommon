package moorcommon.annotation.impl;

import moorcommon.annotation.MoorNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoorNumberImpl implements
        ConstraintValidator<MoorNumber, String> {

    @Override
    public void initialize(MoorNumber contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        Boolean result = false;

        if (contactField != null && contactField.matches("[0-9]+") && (contactField.length() == 10)) {
            result = true;
        }

        return result;
    }

}