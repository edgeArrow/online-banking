package com.nazartsyhaniuk.dev.onlinebanking.validation.password;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        CustomerDto customer = (CustomerDto) obj;
        return customer.getPassword().equals(customer.getMatchingPassword());
    }
}
