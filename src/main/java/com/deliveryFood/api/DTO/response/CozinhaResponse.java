package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Cozinha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaResponse {

	private long id;
	private String nome;
	

	public CozinhaResponse(Cozinha cozinha) {
		this.id=cozinha.getId();
		this.nome=cozinha.getNome();
	}
}
