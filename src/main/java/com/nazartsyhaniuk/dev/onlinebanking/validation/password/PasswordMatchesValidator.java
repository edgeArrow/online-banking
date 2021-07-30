package com.nazartsyhaniuk.dev.onlinebanking.validation.password;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    //TODO
    // temporarily not used
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Customer customer = (Customer) obj;
        return customer.getPassword().equals(customer.getPassword());
    }
}
