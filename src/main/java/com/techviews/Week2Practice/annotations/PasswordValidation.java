package com.techviews.Week2Practice.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {
    String message() default "\"Password should contain at least 1 upper case 1 lower case 1 special character " +
            "and at least 10 charactor long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
