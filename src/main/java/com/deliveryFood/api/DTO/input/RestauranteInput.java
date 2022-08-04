package com.deliveryFood.api.DTO.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deliveryFood.Entity.Cozinha;
import com.deliveryFood.Entity.Restaurante;
import com.deliveryFood.core.validation.TaxaEntrega;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {
	@NotBlank
	private String nome;
	
	@TaxaEntrega
	private BigDecimal taxaEntrega;
	
	@NotNull
	@Positive
	private long cozinhaId;
	
	@NotNull
	@Valid
	private EnderecoInput endereco;
	
	public Restaurante toRestaurante(){
	var restaurante= new Restaurante();
		
		restaurante.setNome(nome);
		restaurante.setTaxaEntrega(taxaEntrega);
	
		var cozinha=new Cozinha();
		cozinha.setId(cozinhaId);
	
		restaurante.setCozinha(cozinha);
		
		restaurante.setEndereco(endereco.toEndereco());
		
		return restaurante;
	}
}
