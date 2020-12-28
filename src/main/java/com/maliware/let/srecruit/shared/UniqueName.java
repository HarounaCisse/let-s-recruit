package com.maliware.let.srecruit.shared;

import org.springframework.messaging.handler.annotation.Payload;

import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "User name already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
