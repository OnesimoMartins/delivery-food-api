package com.deliveryFood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.api.DTO.input.RestauranteInput;
import com.deliveryFood.api.DTO.response.RestauranteResponse;
import com.deliveryFood.domain.exception.CozinhaNaoEncontradaException;
import com.deliveryFood.domain.exception.NegocioException;
import com.deliveryFood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
class RestauranteController {

	@Autowired private RestauranteService restauranteService;

	@GetMapping
	public List<RestauranteResponse> mostrarTodos() {
		return RestauranteResponse.toRestaunteResponseList(
				restauranteService.todosRestaurantes().stream()
				.dropWhile(restaurante->restaurante.getEndereco()==null)
				.collect(Collectors.toList()));//enquanto houver restaurantes sem endereco
	}

	@GetMapping("/{restauranteId}")
	public RestauranteResponse consultar(@PathVariable("restauranteId") final long id) {
		return new RestauranteResponse(restauranteService.buscarOuFalhar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponse cadastrar(@RequestBody @Valid RestauranteInput restauranteInput){
		
		var restaurante= restauranteInput.toRestaurante();
		
	   restaurante =restauranteService.cadatrar(restauranteInput.toRestaurante()); 
		
		return new RestauranteResponse(restaurante); 
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteResponse alterar( @RequestBody @Valid RestauranteInput novoRestauranteInput,
			@PathVariable("restauranteId") long id){
		var restauranteActual=restauranteService.buscarOuFalhar(id); 
		
		var novoRestaurante=novoRestauranteInput.toRestaurante();
		
		BeanUtils.copyProperties(novoRestaurante, restauranteActual,"id","dataCriacao",
				"formasPagamento","endereco");
		restauranteActual.setUltimaActualizacao(OffsetDateTime.now());
		
		try {
			 restauranteActual=restauranteService.actualizar(restauranteActual,id);
			return  new RestauranteResponse(restauranteActual);
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}")
	public void eliminar(@PathVariable("restauranteId") long id){
		restauranteService.remover(id);
	}

	@PutMapping("{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrirRestaurante( @PathVariable long id) {
		restauranteService.abrirRestaurante(id);
	}
	@PutMapping("{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fecharRestaurante( @PathVariable long id) {
		restauranteService.fecharRestaurante(id);
	}
	@PutMapping("{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurante( @PathVariable long id) {
		restauranteService.ativarRestaurante(id);
	}
	@DeleteMapping("{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarRestaurante( @PathVariable long id) {
		restauranteService.desativarRestaurante(id);
	}
}
