package com.deliveryFood.Entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table( name="item_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int quantidade;
	private String observacao;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pedido_id")
	@ToString.Exclude
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	public BigDecimal calcularValorTotal() {
		precoTotal=precoUnitario.multiply(new BigDecimal(quantidade));
		return precoTotal;
	}
}
