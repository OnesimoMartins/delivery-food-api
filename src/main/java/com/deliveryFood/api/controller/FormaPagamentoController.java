package com.deliveryFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.FormaPagamento;
import com.deliveryFood.api.DTO.input.FormaPagamentoInput;
import com.deliveryFood.api.DTO.response.FormaPagamentoResponse;
import com.deliveryFood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired private FormaPagamentoService formaPagamentoService; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse adicionar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
	var formaPagamento=formaPagamentoService.adicionar(formaPagamentoInput.toFormaPagamento());
	return new FormaPagamentoResponse(formaPagamento);
	}
	@GetMapping
	public List<FormaPagamento> mostrarTodas() {
		return this.formaPagamentoService.buscarTodas();
	}
	@GetMapping("/{id}")
	public FormaPagamento buscarPorId(@PathVariable long id) {
		return this.formaPagamentoService.buscarPorIdOuFalhar(id);
	}
	@GetMapping("/buscar")
	public FormaPagamentoResponse buscarPorDescricao(@RequestParam String descricao) {
     var formaPagamento=formaPagamentoService.buscarPorDescricaoOuFalhar(descricao);
		return new FormaPagamentoResponse(formaPagamento);
	}
	@PutMapping("/{id}")
	public FormaPagamentoResponse actualizar(@PathVariable long id,
			@Valid @RequestBody FormaPagamentoInput novaformaPagamentoInput) {
		var novaFormaPagamento=novaformaPagamentoInput.toFormaPagamento();
		novaFormaPagamento.setId(id);
		return new FormaPagamentoResponse( formaPagamentoService.adicionar( novaFormaPagamento ));
		
	}
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirFormaPagamento(@PathVariable long id) {
		formaPagamentoService.excluirFormaPagamento(id);
	}
}
