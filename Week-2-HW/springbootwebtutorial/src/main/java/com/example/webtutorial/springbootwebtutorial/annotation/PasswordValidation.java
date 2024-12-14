package com.example.webtutorial.springbootwebtutorial.annotation;


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

    String message() default "Password must contain atleast 10 characters , atleast 1 uppercase and atleast 1 lowercase character and atleast one special character.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
