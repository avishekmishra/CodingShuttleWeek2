package com.techviews.Week2Practice.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PrimeInputValidator implements ConstraintValidator<PrimeInputValidation, Integer> {

    @Override
    public boolean isValid(Integer inputNo, ConstraintValidatorContext constraintValidatorContext) {
        if (inputNo == null || inputNo <= 1) return false;
        if (inputNo == 2 || inputNo == 3) return true;
        if (inputNo % 2 == 0) return false;

        for (int i = 3; i * i <= inputNo; i += 2) {
            if (inputNo % i == 0) {
                return false;
            }
        }
        return true;
    }
}
