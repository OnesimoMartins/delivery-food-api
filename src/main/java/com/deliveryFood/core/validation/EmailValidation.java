package com.deliveryFood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.deliveryFood.domain.service.UsuarioRepository;

public class EmailValidation implements ConstraintValidator<EmailNaoExisteNaBD,String> {

	@Autowired UsuarioRepository usuarios;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	var emailNadBd=	usuarios.emailExists(value);
		return emailNadBd==null;
	}

}
