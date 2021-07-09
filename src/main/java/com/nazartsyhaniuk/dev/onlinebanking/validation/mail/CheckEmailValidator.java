package com.nazartsyhaniuk.dev.onlinebanking.validation.mail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    private String endOfMail;

    @Override
    public void initialize(CheckEmail checkEmail) {
        endOfMail = checkEmail.value();
    }

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext constraintValidatorContext) {

        return enteredValue.endsWith(endOfMail);
    }
}
