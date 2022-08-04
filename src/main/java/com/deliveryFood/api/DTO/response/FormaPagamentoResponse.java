package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.FormaPagamento;

import lombok.Getter;

@Getter
public class FormaPagamentoResponse {

	private long id;
	private String descricao;

	
	public FormaPagamentoResponse( FormaPagamento formaPagamento) {
		this.descricao=formaPagamento.getDescricao();
		this.id=formaPagamento.getId();
	}
}
