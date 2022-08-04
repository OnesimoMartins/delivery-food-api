package com.deliveryFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Cidade;
import com.deliveryFood.Entity.Provincia;
import com.deliveryFood.domain.exception.CidadeNaoEncontradaException;
import com.deliveryFood.domain.exception.EntidadeEmUsoException;
import com.deliveryFood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private ProvinciaService provinciaService;
	
	@Autowired
	private CidadeRepository cidades;
	
	public Cidade buscarPorIdOuFalhar(long id) {
		return cidades.findById(id).orElseThrow(()->
		new CidadeNaoEncontradaException(id));
	}

	public Cidade cadatrar(Cidade cidade) {
	Provincia provincia= provinciaService.buscarPorIdOuFalhar(cidade.getProvincia().getId());
	   cidade.setProvincia(provincia);
		return cidades.save(cidade);	
	}

	public Cidade actualizar(Cidade novaCidade, long id) {
  
	//verifica se a nova provincia existe ou lança a excessao
	
		Provincia provincia=provinciaService
			         .buscarPorIdOuFalhar(novaCidade.getProvincia().getId());
	
	novaCidade.setProvincia(provincia);
	
	return cidades.save(novaCidade);
	}

	public void remover(long id) {
		try {
        cidades.delete(this.buscarPorIdOuFalhar(id));    
		}catch(DataIntegrityViolationException e) {
			throw new  EntidadeEmUsoException
			("A cidade "+id+" está associada a uma ou mais províncias.");
		}
	}

	
 }
