package com.webapp.foodgate.validation.impl;

import com.webapp.foodgate.validation.PhoneNumKind;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumKindValidation implements ConstraintValidator<PhoneNumKind, String> {
    private String message;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if(phoneNumber==null)
            return true;
        // Implement phone number validation logic here
        // (e.g., regular expression pattern matching)
        return phoneNumber != null && phoneNumber.matches("^\\+\\d{2}\\d{10}$");
    }

    @Override
    public void initialize(PhoneNumKind phoneNumKind) {
        this.message = phoneNumKind.message();
    }
}
