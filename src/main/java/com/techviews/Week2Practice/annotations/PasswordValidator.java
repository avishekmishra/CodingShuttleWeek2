package com.techviews.Week2Practice.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public boolean isValid(String inputString, ConstraintValidatorContext constraintValidatorContext) {
        if (inputString == null) return false;
        if (inputString.length()<10) return false;
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).+$";
        return inputString.matches(regex);
    }
}
