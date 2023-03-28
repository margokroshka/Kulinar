package com.tms.kulinar.validator;

import com.tms.kulinar.annotation.IsNub;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNubValidator implements ConstraintValidator<IsNub,String> {
    @Override
    public void initialize(IsNub constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>10;
    }

}
