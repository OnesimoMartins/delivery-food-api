package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Usuario;

import lombok.Data;

@Data
public class UsuarioResumo {


	private long id;
	private String nome;
	private String email;
	
	public UsuarioResumo(Usuario usuario ) {

		this.email=usuario.getEmail();
		this.id=usuario.getId();
		this.nome=usuario.getNome();
	
	}
}
