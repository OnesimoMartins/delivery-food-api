package com.deliveryFood.api.DTO.response;

import java.math.BigDecimal;

import com.deliveryFood.Entity.ItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemPedidoResponse {

	private final int quantidade;
	private final BigDecimal precoUnitario;
	private final BigDecimal precoTotal;
	
	private final ProdutoResponse produto;
	@Setter
	private String observacao;
	
	public ItemPedidoResponse(ItemPedido pedido) {
	this.observacao=pedido.getObservacao();
	this.precoTotal=pedido.getPrecoTotal();
	this.produto= new ProdutoResponse(pedido.getProduto());
	this.quantidade=pedido.getQuantidade();
	this.precoUnitario=pedido.getPrecoUnitario();
	}
}
