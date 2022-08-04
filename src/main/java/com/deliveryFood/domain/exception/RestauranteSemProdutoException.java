package com.deliveryFood.domain.exception;

import java.util.List;

import com.deliveryFood.api.DTO.response.ProdutoResponse;

import lombok.Getter;

@Getter
public class RestauranteSemProdutoException extends RuntimeException {

	List<ProdutoResponse> produtosEmFalta;
	
	public RestauranteSemProdutoException(List<ProdutoResponse> produtosEmFalta) {
	this.produtosEmFalta=produtosEmFalta;
	}

	private static final long serialVersionUID = 1L;

}
