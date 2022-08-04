package com.deliveryFood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.deliveryFood.Entity.Restaurante;

public interface RestauranteRepositoryQueries {

	public List<Restaurante> consultarRestaurantesLike( String nome);
	
	public List<Restaurante> consultarPorNomeAndTaxaEntrega(String nome
			,BigDecimal taxaInicial,BigDecimal taxaFinal);
	
	List<Restaurante> allResataurantesSQL();
}
