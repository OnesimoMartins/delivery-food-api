package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;

import com.deliveryFood.Entity.FormaPagamento;
import com.deliveryFood.core.validation.FormaPagamentoNotExistsOnDB;

import lombok.Setter;

@Setter
public class FormaPagamentoInput {
	
	@NotBlank @FormaPagamentoNotExistsOnDB
	private String descricao; 
	
	public FormaPagamento toFormaPagamento() {
		FormaPagamento formaPagamento=new FormaPagamento();
		formaPagamento.setDescricao(descricao);
		return formaPagamento;	
	}
}
