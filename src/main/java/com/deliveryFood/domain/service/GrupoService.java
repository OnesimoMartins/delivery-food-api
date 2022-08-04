package com.deliveryFood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Grupo;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.repository.GrupoRepository;
import com.deliveryFood.domain.repository.PermissaoRepository;

@Service
public class GrupoService {

	@Autowired private GrupoRepository grupos;
	@Autowired private PermissaoRepository permissoes;
	public List<Grupo> todos(){
		return grupos.findAll();
	}
	
	public Grupo porIdOuFalhar(long id){
		return grupos.findById( id).orElseThrow(
				()->new EntidadeNaoEncontradaException("Não existe Grupo com o id "+id)
				);
	}
	public Grupo salvar(Grupo grupo) {
		return grupos.save(grupo);
	}
	@Transactional
	public void associarPermissao(long grupoId,long permissaoId) {
		var permissao=permissoes.findById(permissaoId).orElseThrow(
				()->new EntidadeNaoEncontradaException("Não existe permissao com o id "+permissaoId));
		porIdOuFalhar(grupoId).getPermissoes().add(permissao);
	}
	
	@Transactional
	public void desassociarPermissao(long grupoId, long permissaoId) {
		var permissao=permissoes.findById(permissaoId).orElseThrow(
				()->new EntidadeNaoEncontradaException("Não existe permissao com o id "+permissaoId));
		porIdOuFalhar(grupoId).getPermissoes().remove(permissao);		
	}
}
