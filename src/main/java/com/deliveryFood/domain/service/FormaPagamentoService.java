package com.deliveryFood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.FormaPagamento;
import com.deliveryFood.domain.exception.EntidadeEmUsoException;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.repository.FormaPagamentoRepository;


@Service 
public class FormaPagamentoService {

	@Autowired private FormaPagamentoRepository formaPagamentos;
	 
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return formaPagamentos.save(formaPagamento);
	}
	public FormaPagamento buscarPorIdOuFalhar(long id) {
	return formaPagamentos.findById(id).orElseThrow(
				()-> new EntidadeNaoEncontradaException
				            ("forma de pagamento com o Id "+id+" não encontrada.")
				);
	}
	
	public FormaPagamento buscarPorDescricaoOuFalhar(String descricao) {
	return	formaPagamentos.findFormaPagamentoByDescricao(descricao)//.get();
		.orElseThrow( ()->new EntidadeNaoEncontradaException("Não existe forma de pagamento com"
				+ " a descrição '"+descricao+"'") 
				);
	}
	public boolean formaPagamentoExistente(String value) {
        return formaPagamentos.findFormaPagamentoByDescricao(value).isPresent();
	}
	public void excluirFormaPagamento(long id) {
		try{
			formaPagamentos.deleteById(id);
	
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Esta forma de pagamento está em uso por um ou mais Restaurantes");
		}
	}
	public List<FormaPagamento> buscarTodas() {
		return formaPagamentos.findAll();
	}
	
}
