package com.gfk.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface Phone {
	
	String message() default "Allowed character are [0-9,(),-] ";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};

}
