package moorcommon.annotation.impl;


import moorcommon.annotation.MoorEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoorEmailImpl implements
        ConstraintValidator<MoorEmail, String> {

    @Override
    public void initialize(MoorEmail contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        Boolean result = false;
        /*
        Email Validation:
        "^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"
       */

        if (contactField != null && contactField.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            result = true;
        }

        return result;
    }

}