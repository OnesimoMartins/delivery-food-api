package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;

import com.deliveryFood.Entity.Cozinha;

import lombok.Setter;

@Setter
public class CozinhaInput {
	@NotBlank private String nome;

	public Cozinha toCozinha() {
		var cozinha= new Cozinha();
		cozinha.setNome(nome);
		return cozinha;
	} 
}
