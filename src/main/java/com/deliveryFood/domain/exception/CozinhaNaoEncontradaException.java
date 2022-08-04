package com.deliveryFood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(long id) {
		super("Não existe cozinha com o código "+id);
		// TODO Auto-generated constructor stub
	}
}
