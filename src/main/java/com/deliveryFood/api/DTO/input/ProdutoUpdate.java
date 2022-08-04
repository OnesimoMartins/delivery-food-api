package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deliveryFood.Entity.Produto;

import lombok.Setter;

@Setter
public class ProdutoUpdate {

	@NotBlank
	private String nome;
	
	@NotNull
	@NotBlank
	private boolean ativo;
	
	public Produto toProduto() {
		var produto=new Produto();
		produto.setNome(nome);
		produto.setAtivo(ativo);
		return produto;
	}
}
