package com.deliveryFood.domain.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Provincia;
import com.deliveryFood.domain.exception.EntidadeEmUsoException;
import com.deliveryFood.domain.exception.ProvinciaNaoEncontradaException;
import com.deliveryFood.domain.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired private ProvinciaRepository provincias;
	
	public Provincia buscarPorIdOuFalhar(long id) {
		return provincias.findById(id).orElseThrow(
		()->new ProvinciaNaoEncontradaException(+id));
	}
	
	public Provincia inserir(Provincia provincia) {
		return provincias.save(provincia);
	}
	
	public Provincia actualizar(Provincia novaProvincia, long id) {
		Provincia provinciaActual = this.buscarPorIdOuFalhar(id); // provincias.findById(id);
		BeanUtils.copyProperties(novaProvincia,provinciaActual,"id");
		return provincias.save(provinciaActual);
	}

   public void eliminar(long id) {
	
	  try {
		  provincias.delete(this.buscarPorIdOuFalhar(id));
	    }catch( DataIntegrityViolationException e) {
    	 throw 
    	 new EntidadeEmUsoException
    	 ("A Provincia "+id+" esta associada a uma ou mais cidades");
         }
	          
   }
}
 