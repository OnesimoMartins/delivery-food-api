package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Cidade;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResponse {

	private final Long id;
	private final String nome;
	private final ProvinciaResponse provincia;

	public CidadeResponse(Cidade cidade) {
	this.id=cidade.getId();
	this.nome=cidade.getNome();
	var provinciaResponse=new ProvinciaResponse( cidade.getProvincia());
	this.provincia=provinciaResponse;
	}



}
