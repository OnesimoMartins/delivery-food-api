package com.deliveryFood.api.DTO.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deliveryFood.Entity.ItemPedido;
import com.deliveryFood.Entity.Produto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class ItemPedidoInput {

	@NotNull
	private long produtoId;
	@Size(min = 1)
	private int quantidade;
	private BigDecimal precoUnitario;
	private String observacao;

  
  public ItemPedido	toItemPedido() {
	  var item=new ItemPedido();
	
	  item.setPrecoUnitario(precoUnitario);
	  item.setPrecoTotal(precoUnitario.multiply(BigDecimal.valueOf(quantidade)));
		  
	  item.setProduto(new Produto(produtoId));
	  
	  if(observacao!=null) item.setObservacao(observacao);
	  
	  System.out.println(item.getPrecoTotal());//DEBUG
	  
	  return item;
  }

}
