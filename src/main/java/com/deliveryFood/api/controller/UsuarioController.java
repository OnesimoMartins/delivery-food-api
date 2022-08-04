package com.deliveryFood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.api.DTO.input.UsuarioInput;
import com.deliveryFood.api.DTO.input.UsuarioInputUpdate;
import com.deliveryFood.api.DTO.response.UsuarioResponse;
import com.deliveryFood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

	private @Autowired UsuarioService usuarioService;

	@PostMapping
	public UsuarioResponse criarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {
        var usuario=usuarioService.criarUsuario(usuarioInput.toUsuario());
        return new UsuarioResponse(usuario);
	}
	@PutMapping("/{id}")
	public UsuarioResponse actualizarUsuario(@RequestBody @Valid UsuarioInputUpdate usuarioInput,
			@PathVariable long id) {
	
		var usuarioNovo=usuarioInput.toUsuario();
		    usuarioNovo.setId(id);
		
		var usuarioActual=usuarioService.buscarPorIdOuFalhar(id);
		
		 usuarioActual= usuarioService.actualizar(usuarioActual,usuarioNovo);
	
		return new UsuarioResponse( usuarioActual );
	
	}
}
