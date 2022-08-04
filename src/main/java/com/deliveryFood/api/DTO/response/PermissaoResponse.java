package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Permissao;

import lombok.Getter;

@Getter
public class PermissaoResponse {

	private long id;
	private String nome;
	private String descricao;
	
	public PermissaoResponse(Permissao permissao) {
	this.id=permissao.getId();
	this.nome=permissao.getNome();
	this.descricao=permissao.getDescricao();
	}
}
