package com.deliveryFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.api.DTO.input.ProdutoInput;
import com.deliveryFood.api.DTO.input.ProdutoUpdate;
import com.deliveryFood.api.DTO.response.ProdutoResponse;
import com.deliveryFood.domain.service.ProdutoService;
import com.deliveryFood.domain.service.RestauranteService;
import com.deliveryFood.util.ResponseUtils;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {
	
	@Autowired private ProdutoService produtoService;
	@Autowired private RestauranteService restauranteService;

	@GetMapping
	public List<ProdutoResponse> todos(@PathVariable long restauranteId) {
		return ResponseUtils.toProdutoResponseList( 
				restauranteService.buscarOuFalhar(restauranteId).getProdutos()
				
				);
	}
	
	@PutMapping("/{id}")
	public ProdutoResponse (@PathVariable long id,@PathVariable long restauranteId) {
		return new ProdutoResponse(
				restauranteService.buscarOuFalhar(restauranteId).
				);
	}
	@PostMapping
	public ProdutoResponse criar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable long restauranteId) {
		
		return new ProdutoResponse( 
				produtoService.adicionar(produtoInput.toProduto()
						));
	}
	
	
	@PutMapping("/{id}")
	public ProdutoResponse actualizar(@RequestBody @Valid ProdutoUpdate produtoInput,
			@PathVariable long id) {
		return new ProdutoResponse( produtoService.actualizar(produtoInput.toProduto(),id));
	}
	
	
}
