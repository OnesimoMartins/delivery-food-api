package com.deliveryFood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.Provincia;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.repository.ProvinciaRepository;
import com.deliveryFood.domain.service.ProvinciaService;

@RestController
@RequestMapping("/provincias")
class ProvinciaController {

	@Autowired
	private ProvinciaRepository provincias;
	@Autowired
	private ProvinciaService provinciaService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Provincia> listar() {
		return provincias.findAll();
	}

	@GetMapping("/{provinciaId}")
	public Provincia buscarPorId(@PathVariable("provinciaId") long id) {
		return provinciaService.buscarPorIdOuFalhar(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Provincia cadastrar(@RequestBody Provincia provincia) {
		return provinciaService.inserir(provincia);
	}

	@PutMapping("/{provinciaId}")
	public ResponseEntity<?> actuaizar(@PathVariable("provinciaId") long id,
			@RequestBody Provincia provincia) {
		
		try {// refactorar put

			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(provinciaService.actualizar(provincia, id));

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}


	@DeleteMapping("/{provinciaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("provinciaId") long id) {
			provinciaService.eliminar(id);				
  }

}