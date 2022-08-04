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

import com.deliveryFood.api.DTO.response.PermissaoResponse;
import com.deliveryFood.domain.service.GrupoService;
import com.deliveryFood.util.ResponseUtils;

@RequestMapping("/grupos/{grupoId}/permissoes")
@RestController
public class GrupoPermissaoController {

	@Autowired GrupoService grupoService;
	@GetMapping
	public Set<PermissaoResponse> mostrarPermissoes(@PathVariable long grupoId) {
		return
				ResponseUtils.toPermissaoSet(
				grupoService.porIdOuFalhar(grupoId).getPermissoes()
	         );
	}
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @PutMapping("/{permissaoId}")
   public void adicionarPermissao(@PathVariable long grupoId,@PathVariable long permissaoId) {
	   grupoService.associarPermissao(grupoId, permissaoId);
   }
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{permissaoId}")
   public void removerPermissao(@PathVariable long grupoId,@PathVariable long permissaoId) {
	   grupoService.desassociarPermissao(grupoId, permissaoId);
   }
}
