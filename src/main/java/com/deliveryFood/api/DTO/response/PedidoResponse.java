package com.deliveryFood.api.DTO.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.deliveryFood.Entity.Pedido;
import com.deliveryFood.Enum.StatusPedido;
import com.deliveryFood.util.ResponseUtils;

import lombok.Getter;

@Getter
public class PedidoResponse {

 final private String codigo;
 final private BigDecimal valorTotal;
 final private StatusPedido statusPedido;
 private OffsetDateTime dataCriacao;
 final private String enderecoEntrega;
 final private String formaPagamento;
 final private List<ItemPedidoResponse> itens;
      
 	public PedidoResponse(Pedido pedido) {

 	this.itens=ResponseUtils.toItemPedidoResponseList(pedido.getItens());
 	itens.stream().filter(x->x.getObservacao()==null).forEach(x->x.setObservacao("sem observação"));;
   this.dataCriacao= pedido.getDataCriacao();	
   this.formaPagamento= pedido.getFormaPagamento().getDescricao();
   this.dataCriacao=pedido.getDataCriacao();
   this.statusPedido=pedido.getStatusPedido();
   this.valorTotal=pedido.getValorTotal();
   this.codigo=pedido.getCodigo();
   
   var endereco=pedido.getEnderecoEntrega();
    String str=(endereco.getCidade().getProvincia().getNome()+","+endereco.getCidade().getNome()+","+ 
   endereco.getMunicipio()+","+endereco.getBairro()+", rua "	+endereco.getRua());
   this.enderecoEntrega=str;
 
}

}
