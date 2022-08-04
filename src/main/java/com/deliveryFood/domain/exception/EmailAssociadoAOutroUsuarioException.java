package com.deliveryFood.domain.exception;

public class EmailAssociadoAOutroUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailAssociadoAOutroUsuarioException(String msg) {
		super(msg);
	}
}
