package com.deliveryFood.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Pedido;
import com.deliveryFood.Enum.StatusPedido;
import com.deliveryFood.api.DTO.response.ProdutoResponse;
import com.deliveryFood.domain.exception.NegocioException;
import com.deliveryFood.domain.exception.RestauranteSemProdutoException;
import com.deliveryFood.domain.repository.PedidoRepositoy;

@Service
public class PedidoService {

	@Autowired private PedidoRepositoy pedidos;
	
	@Autowired private RestauranteService restauranteService;
	@Autowired private FormaPagamentoService formaPagamentoService;
	@Autowired private CidadeService cidadeService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private ProdutoService produtoService;
	
	@Transactional
	public Pedido criarPedido (Pedido pedido) {
		// Cria o objecto Produto de cada item baseado no seu id;
		pedido.setStatusPedido(StatusPedido.CRIADO);
		
		validarPedido(pedido);
        validarItens(pedido);
        
		pedido.setValorTotal(calcularPrecoTotalPedido(pedido));
		pedido.setCodigo(UUID.randomUUID().toString());
		return pedidos.save(pedido);
	}
	
	private void validarPedido(Pedido pedido) {
		
		var restaurante=restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		var usuario=usuarioService.buscarPorIdOuFalhar(pedido.getCliente().getId());
		var formaPagamento= formaPagamentoService.buscarPorIdOuFalhar(pedido.getFormaPagamento().getId());
		var cidade= cidadeService.buscarPorIdOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(usuario);
		pedido.setTaxaEntrega(restaurante.getTaxaEntrega());

		if(!pedido.getRestaurante().consultarSeAceitaFomaDePagamento(pedido.getFormaPagamento()))
			throw new NegocioException("Esta forma de pagamento não é aceite neste restaurante"); 
		
	}
	
	private void validarItens(Pedido pedido) {
		List<ProdutoResponse> produtosEmFalta=new ArrayList<>();
		
		pedido.getItens().parallelStream().forEach(
				itemPedido->{
					
					var produto= produtoService.buscarPorIdOuFalhar(itemPedido.getProduto().getId());
					
					if(!pedido.getRestaurante().consultarProduto(produto)) { 
					 produtosEmFalta.add(new ProdutoResponse(produto));
					 }else {
						 itemPedido.setProduto(produto);
						 System.out.println(itemPedido.toString());
						 itemPedido.setPedido(pedido);
					 } 
				  }
			 );
		
		if(!produtosEmFalta.isEmpty())throw new RestauranteSemProdutoException(produtosEmFalta);
	}
	
	//@Transactional
	private BigDecimal calcularPrecoTotalPedido(Pedido pedido) {
		return 
				pedido.getItens()
				.parallelStream()
				.map(x-> x.calcularValorTotal())
				.reduce( BigDecimal.ZERO, BigDecimal ::add)
				.add(pedido.getTaxaEntrega());
	}

	Pedido buscarPorid(String value) {
		return pedidos.findPedidoByCodigo(value).orElseThrow(
				()->new NegocioException("pedido inexistente.")
				);
	}
	
	@Transactional
	public void cancelarPedido(String codigo) {
	 var pedido=buscarPorid(codigo);
	pedido.setDataCancelamento(OffsetDateTime.now());
	pedido.setStatusPedido(StatusPedido.CANCELADO);
	}
}
