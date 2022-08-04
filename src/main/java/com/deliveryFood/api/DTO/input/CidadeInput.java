package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deliveryFood.Entity.Cidade;
import com.deliveryFood.Entity.Provincia;

import lombok.Setter;

@Setter
public class CidadeInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private long provinciaId;
	
	public Cidade toCidade() {
		var cidade= new Cidade();
		cidade.setNome(nome);
		
		var provincia=new Provincia();
		provincia.setId(provinciaId);
		
		cidade.setProvincia(provincia);
		return cidade;
	}
}
