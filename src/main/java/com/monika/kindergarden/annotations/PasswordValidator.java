package com.monika.kindergarden.annotations;

import com.monika.kindergarden.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy= PasswordStrengthValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
