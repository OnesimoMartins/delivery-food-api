package com.deliveryFood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(long id) {
		super("Não foi encontrada nenhuma cidade com o código "+id);
		// TODO Auto-generated constructor stub
	}

}
