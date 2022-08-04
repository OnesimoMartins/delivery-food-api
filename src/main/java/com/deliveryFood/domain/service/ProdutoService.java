package com.deliveryFood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Produto;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired private ProdutoRepository produtos;
	
	public List<Produto> todos() {
		return produtos.findAll();
	}

	public Produto buscarPorIdOuFalhar(long id) {
		return produtos.findById(id).orElseThrow(()->new EntidadeNaoEncontradaException(
				"produto com o id "+id+" não encontrado"
				));
	}

	public Produto adicionar(Produto produto) {
		produto.setAtivo(true);
		return produtos.save(produto);
	}

	public Produto actualizar(Produto produtoUpdate, long IdProdutoActual) {
		var produtoActual=buscarPorIdOuFalhar(IdProdutoActual);
		
		if(produtoUpdate.getNome()!=null)  produtoActual.setNome(produtoUpdate.getNome());		
		
		produtoActual.setAtivo(produtoUpdate.isAtivo());
		
		return produtoActual;
	}
}
