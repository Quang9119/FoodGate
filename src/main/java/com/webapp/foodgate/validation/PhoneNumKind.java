package com.webapp.foodgate.validation;

import com.webapp.foodgate.validation.impl.PhoneNumKindValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumKindValidation.class)
public @interface PhoneNumKind {
    String message() default "Invalid phone number format. Must start with '+' followed by 2 digits and 10 numbers.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
