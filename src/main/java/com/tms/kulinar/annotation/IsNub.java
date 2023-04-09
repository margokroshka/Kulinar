package com.tms.kulinar.annotation;

import com.tms.kulinar.validator.IsNubValidator;
import org.springframework.http.HttpStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsNubValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNub {
    String message() default "Go to study!Puppy!";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
