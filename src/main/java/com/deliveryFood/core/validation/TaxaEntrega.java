package com.deliveryFood.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@PositiveOrZero
@NotNull
@Constraint(validatedBy = { })
public @interface TaxaEntrega {

	@OverridesAttribute(constraint=PositiveOrZero.class,name="message")
	@OverridesAttribute(constraint=NotNull.class,name="message")
	String message() default "{TaxaEntrega.invalida}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
