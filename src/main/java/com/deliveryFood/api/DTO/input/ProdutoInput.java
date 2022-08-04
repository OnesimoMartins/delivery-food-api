package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;

import com.deliveryFood.Entity.Produto;

import lombok.Setter;

@Setter
public class ProdutoInput {
	
	@NotBlank
	private String nome;
	
	public Produto toProduto() {
		var produto=new Produto();
		produto.setNome(nome);
		return produto;
	}

}
