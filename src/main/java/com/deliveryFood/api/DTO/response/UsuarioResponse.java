package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Usuario;

import lombok.Getter;

@Getter
public class UsuarioResponse {

	private long id;
	private String nome;
	private String email;
	
	public UsuarioResponse(Usuario usuario ) {

		this.email=usuario.getEmail();
		this.id=usuario.getId();
		this.nome=usuario.getNome();
	
	}
}
