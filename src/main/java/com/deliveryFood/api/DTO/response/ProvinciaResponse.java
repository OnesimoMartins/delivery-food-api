package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Provincia;

import lombok.Getter;

@Getter
public class ProvinciaResponse {
	 final private long id; 
	 final private String nome;
	  
	  public ProvinciaResponse( Provincia provincia) {
		  this.id=provincia.getId();
		  this.nome=provincia.getNome();
      }
}
