package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Grupo;

import lombok.Getter;

@Getter
public class GrupoResponse {

	final long id;
	final String nome;
	//final Set<Permissao> permisoes;

 public GrupoResponse(Grupo grupo) {
     this.nome=grupo.getNome();
     this.id=grupo.getId();
     //this.permisoes=grupo.getPermissoes();
 }


}
