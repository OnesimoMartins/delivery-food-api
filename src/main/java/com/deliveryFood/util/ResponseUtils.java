package com.deliveryFood.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.deliveryFood.Entity.Cidade;
import com.deliveryFood.Entity.Grupo;
import com.deliveryFood.Entity.ItemPedido;
import com.deliveryFood.Entity.Permissao;
import com.deliveryFood.Entity.Produto;
import com.deliveryFood.api.DTO.response.CidadeResponse;
import com.deliveryFood.api.DTO.response.GrupoResponse;
import com.deliveryFood.api.DTO.response.ItemPedidoResponse;
import com.deliveryFood.api.DTO.response.PermissaoResponse;
import com.deliveryFood.api.DTO.response.ProdutoResponse;

public abstract class ResponseUtils {

	public static List<GrupoResponse> toGrupoList(List<Grupo> grupos) {
		List<GrupoResponse> gruposResponse= new ArrayList<>();
		grupos.forEach(g->gruposResponse.add(new GrupoResponse(g)));
	            
		return gruposResponse;
	}
	public static List<CidadeResponse> toCidadeResponseList(List<Cidade> cidades) {
		List<CidadeResponse>cidadesResponse = new ArrayList<>();
		cidades.forEach(x -> cidadesResponse.add(new CidadeResponse(x)));
		return cidadesResponse;
	}
	public static Set<PermissaoResponse> toPermissaoSet(Set<Permissao> permissoes){
		var response=new HashSet<PermissaoResponse>();
		
		permissoes.forEach(x->{
			System.out.println(x.toString());
			response.add(new PermissaoResponse(x));
		});
		
	return response;
	}
	public static List<ProdutoResponse> toProdutoResponseList(List<Produto> produtos) {
		List<ProdutoResponse>produtosResponse = new ArrayList<>();
		produtos.forEach(x -> produtosResponse.add(new ProdutoResponse(x)));
		return produtosResponse;
	}
	public static List<ItemPedidoResponse> toItemPedidoResponseList(List<ItemPedido> itens) {
		List<ItemPedidoResponse> itensResponse= new ArrayList<>();
		
		itens.forEach(x->itensResponse.add(new ItemPedidoResponse(x)));
		
		return itensResponse;
	}
	
}
