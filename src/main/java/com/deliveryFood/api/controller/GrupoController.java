package com.deliveryFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.Grupo;
import com.deliveryFood.api.DTO.input.GrupoInput;
import com.deliveryFood.api.DTO.response.GrupoResponse;
import com.deliveryFood.domain.service.GrupoService;
import com.deliveryFood.util.ResponseUtils;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired private GrupoService grupoService;
	
	@GetMapping
	public List<GrupoResponse> mostrarTodos(){
		return ResponseUtils.toGrupoList(grupoService.todos());
	}
	
	@GetMapping("/{id}")
	public Grupo buscarUm(@PathVariable long id) {
		return grupoService.porIdOuFalhar( id );
	}
	
	@PostMapping
	public GrupoResponse adicionar(@Valid @RequestBody GrupoInput grupoInput) {
		var grupo=grupoService.salvar(grupoInput.toGrupo());
	return new GrupoResponse(grupo);
	}
}
