package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Produto;

import lombok.Getter;

@Getter
public class ProdutoResponse {

	final private long id;	
	
	final private String nome;
	
	final private boolean ativo;
	
	public ProdutoResponse(Produto produto) {

		this.ativo=produto.isAtivo();
		this.id=produto.getId();
		this.nome=produto.getNome();
	}
}
