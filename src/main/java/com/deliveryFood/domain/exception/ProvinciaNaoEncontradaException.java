package com.deliveryFood.domain.exception;

public class ProvinciaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProvinciaNaoEncontradaException(long id) {
		super("Não foi encontrada nenhuma provincia com o código "+id);
		// TODO Auto-generated constructor stub
	}
}
