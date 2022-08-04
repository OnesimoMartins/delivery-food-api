package com.deliveryFood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.deliveryFood.domain.service.FormaPagamentoService;

public class FormaPagamentoNotExistsOnDBValidation implements ConstraintValidator<FormaPagamentoNotExistsOnDB, String>
{

	@Autowired private FormaPagamentoService formaPagamentoService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !formaPagamentoService.formaPagamentoExistente(value);
	
	}
}
