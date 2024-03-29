package com.deliveryFood.core.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= {EmailValidation.class})

public @interface EmailNaoExisteNaBD {

	String message() default "este email já está a ser utilizado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };	
	
}
