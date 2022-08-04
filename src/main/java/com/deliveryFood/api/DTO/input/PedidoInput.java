package com.deliveryFood.api.DTO.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.deliveryFood.Entity.FormaPagamento;
import com.deliveryFood.Entity.ItemPedido;
import com.deliveryFood.Entity.Pedido;
import com.deliveryFood.Entity.Produto;
import com.deliveryFood.Entity.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInput {
	
	private EnderecoInput endereco;
	
	@Positive
	private long restauranteId;	
	
	@Size(min=1)
	@NotNull
	private List<ItemPedidoInput> itens;
	
	@NotNull
	private long formaPagamentoId;
	
	public Pedido toPedido() {
		var pedido=new Pedido();
		
		pedido.setRestaurante(new Restaurante(restauranteId));
		
		pedido.setEnderecoEntrega(endereco.toEndereco());
		
		pedido.setFormaPagamento(new FormaPagamento(formaPagamentoId));
		
		 pedido.setItens(toItemPedidoList( itens ));
		
		return pedido;
	};
	
	private  List<ItemPedido> toItemPedidoList(List<ItemPedidoInput> itens) {
		List<ItemPedido>itemPedidos = new ArrayList<>();
		itens.forEach( x->{
		
		var produto=new Produto();
		produto.setId(x.getProdutoId());
		
		var itemPedido=new ItemPedido();
		itemPedido.setQuantidade(x.getQuantidade());
		itemPedido.setObservacao(x.getObservacao());
		itemPedido.setPrecoUnitario(x.getPrecoUnitario());
		itemPedido.setProduto(produto);
	   
		itemPedidos.add(itemPedido);
	
		}
	);
		return itemPedidos;
	}
	
	
}
