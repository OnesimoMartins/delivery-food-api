package com.deliveryFood.api.DTO.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.deliveryFood.Entity.Restaurante;

import lombok.Getter;

@Getter
public class RestauranteResponse {

	private final Long id;
	private final String nome;
	private final BigDecimal taxaEntrega;
	private final CozinhaResponse cozinha;
	private final EnderecoResponse endereco;
	
	public RestauranteResponse(Restaurante restaurante) {
		this.id=restaurante.getId();
		this.nome=restaurante.getNome();
		this.taxaEntrega=restaurante.getTaxaEntrega();
		this.cozinha=new CozinhaResponse(restaurante.getCozinha());
		this.endereco=new EnderecoResponse(restaurante.getEndereco());
	}
	
	public static List<RestauranteResponse> toRestaunteResponseList(List<Restaurante> restaurantes){
		List<RestauranteResponse> restaurantesDto=new ArrayList<>();
		restaurantes.forEach(x->restaurantesDto.add( new RestauranteResponse(x)));
		return restaurantesDto;
	}

}
