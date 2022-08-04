package com.deliveryFood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Usuario;
import com.deliveryFood.domain.exception.EmailAssociadoAOutroUsuarioException;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired private GrupoService grupoService;

	public Usuario criarUsuario(Usuario usuario) {
		return usuarios.save(usuario);
	}

	public Usuario actualizar(Usuario usuarioActual, Usuario usuarioNovo) {
		String email = usuarios.emailExists(usuarioNovo.getEmail());// MELHORAR

		// verificar se o novo email ja esta associado a um usuario diferente

		if (email != null && usuarios.findByEmail(email).getId() != usuarioNovo.getId()) {
			throw new EmailAssociadoAOutroUsuarioException
			("'" + email + "' já está associado a um usuário");
		}

		BeanUtils.copyProperties(usuarioNovo, usuarioActual, "id", "dataCriacao", "grupos");
		return criarUsuario(usuarioActual);
	}

	public Usuario buscarPorIdOuFalhar(long id) {
		return usuarios.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe usuário com o id " + id));
	}
	
	public void adicionarPermi() {
		
	}

	@Transactional
	public void associarAoGrupo(long usuarioId, long grupoId) {
      Usuario usuario=buscarPorIdOuFalhar(usuarioId);
      usuario.adicionarGrupo(grupoService.porIdOuFalhar(grupoId));
	}
	@Transactional
	public void desassociarAoGrupo(long usuarioId, long grupoId) {
      Usuario usuario=buscarPorIdOuFalhar(usuarioId);
      usuario.removerGrupo(grupoService.porIdOuFalhar(grupoId));
	}
}
