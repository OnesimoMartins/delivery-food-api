package com.deliveryFood.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.deliveryFood.Entity.Cozinha;
import com.deliveryFood.api.DTO.input.CozinhaInput;
import com.deliveryFood.api.DTO.response.CozinhaResponse;
import com.deliveryFood.domain.repository.CozinhaRepository;
import com.deliveryFood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
class CozinhaController {

    @Autowired
	private CozinhaRepository cozinhas;
	@Autowired
	private CozinhaService cozinhaService;

	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CozinhaResponse> listar() {
		return Cozinha.toCozinhaResponseList(cozinhas.findAll());
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaResponse buscarPorId(@PathVariable("cozinhaId") long id) {
	return new CozinhaResponse(
			cozinhaService.buscarOuFalhar(id));
	}
	

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaResponse cadastrar(@RequestBody @Valid CozinhaInput cozinhaInput) {
	    var cozinha=cozinhaInput.toCozinha();
		return  new CozinhaResponse(
				cozinhaService.inserir(cozinha)
				);
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaResponse actuaizar(@PathVariable("cozinhaId") long id,
			                      @RequestBody @Valid CozinhaInput cozinhaInput) {
		return new CozinhaResponse(
				cozinhaService.actualizar(cozinhaInput.toCozinha(), id)
			);
	}	

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("cozinhaId") long id ){
		cozinhaService.eliminar(id);
	}

}
