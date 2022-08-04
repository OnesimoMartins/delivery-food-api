package com.deliveryFood.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="forma_pagamento")
public class FormaPagamento {

	public FormaPagamento(@NotNull long formaPagamentoId) {
	this.id=formaPagamentoId;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String descricao;
}
