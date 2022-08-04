package com.deliveryFood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.Restaurante;
import com.deliveryFood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
class ControllerTest {

	@Autowired private RestauranteRepository restaurantes;
	@GetMapping
	public List<Restaurante> getByNome(@RequestParam("nome")String nome){
		//restaurantes("sabores").forEach(System.out::println);
		return restaurantes.findByNome(nome);
		}

	@GetMapping("/taxaEntrega")
	public List<Restaurante> getByTaxa(BigDecimal taxaInicial,BigDecimal taxaFinal){
		System.out.println("entrou");
		return restaurantes.findByTaxaEntregaBetween(taxaInicial, taxaFinal);
	}
	@GetMapping("/nome-cozinha")
	public List<Restaurante> nomeCozinha(String nome,String nomeCozinha){
		System.out.println("entrou");
return restaurantes.consultarPorNome(nome, nomeCozinha);
	}

	@GetMapping("/count-restaurante")
	public int countBycozinha(long idCozinha){
		System.out.println("entrou");
		return restaurantes.countByCozinhaId(idCozinha);
	
	}
	@GetMapping("/restaurante-nome")
	public List<Restaurante> nomeRestauranteLike(String nome){
		System.out.println("entrou");
		return restaurantes.consultarRestaurantesLike(nome);
	
	}
	@GetMapping("/restaurante-nome-taxas")
	public List<Restaurante> nomeRestauranteLike(String nome, 
			BigDecimal taxaInicial,BigDecimal taxaFinal){
		//System.out.println("entrou");
		return restaurantes.consultarPorNomeAndTaxaEntrega(nome, taxaInicial, taxaFinal);
	
	}
	@GetMapping("/restaurantesSQL")
	public List<Restaurante> restaurantesSql(){
			return restaurantes.allResataurantesSQL();
	}
}