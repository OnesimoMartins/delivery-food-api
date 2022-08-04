package com.deliveryFood.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.FormaPagamento;
import com.deliveryFood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
class RestauranteFormaPagamentoController {
	@Autowired private RestauranteService restauranteService;
	

	@GetMapping
	public Set<FormaPagamento> formasPagamentoRestaurante(@PathVariable long restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId).getFormasPagamento();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void adicionarFormaPagamento(@PathVariable long restauranteId, @PathVariable long id) {
		restauranteService.associarFormaPagamento(restauranteId, id);
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void removerFormaPagamento(@PathVariable long restauranteId, @PathVariable long id) {
		restauranteService.desassociarFormaPagamneto(restauranteId, id);
	}
}
