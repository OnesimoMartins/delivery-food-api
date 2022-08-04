package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;

import com.deliveryFood.Entity.Grupo;

import lombok.Setter;

@Setter
public class GrupoInput {

	@NotBlank
	private String nome;
	
	public Grupo toGrupo() {
		var grupo=new Grupo();
		grupo.setNome(nome);
		return grupo;
	}
	
}
