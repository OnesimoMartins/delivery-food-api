package com.deliveryFood.domain.service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Cozinha;
import com.deliveryFood.domain.exception.CozinhaNaoEncontradaException;
import com.deliveryFood.domain.exception.EntidadeEmUsoException;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhas;
	
	public Cozinha buscarOuFalhar(long id) {
		return cozinhas.findById(id).orElseThrow(()-> new CozinhaNaoEncontradaException(id));
	}
	
	@Transactional
	public Cozinha inserir(Cozinha cozinha) {
		return cozinhas.save(cozinha);
	}
	
	@Transactional
	public void eliminar(long id) {
	   try { 
	 	 
    	 cozinhas.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(
				   "Não existe cozinha com o código "+id));
    	 cozinhas.deleteById(id);
    	 cozinhas.flush();
	     
	     }catch(DataIntegrityViolationException  e) {
	    	 throw new EntidadeEmUsoException("A cozinha "+id+" esta associada a um ou mais restaurantes");
	     }catch (EntityNotFoundException e) {
			throw new CozinhaNaoEncontradaException(id);
		 }
	      //tem problemas, ver episodio 29
		
		 //Cannot delete or update a parent row: a foreign key constraint fails (`deliveryfood`.`restaurante`
		   //, CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`))
	       //throw new EmptyResultData
	}
	
	public Cozinha actualizar(Cozinha cozinha,long id) {
	   Cozinha cozinhaActual = this.buscarOuFalhar(id);;
			BeanUtils.copyProperties(cozinha, cozinhaActual, "id");
			return cozinhas.save(cozinhaActual);
			
	}
}
