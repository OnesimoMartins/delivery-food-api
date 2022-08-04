package com.deliveryFood.domain.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryFood.Entity.Cozinha;
import com.deliveryFood.Entity.Restaurante;
import com.deliveryFood.domain.exception.RestauranteNaoEncontradoException;
import com.deliveryFood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	//fazer validacoes
	@Autowired
	private RestauranteRepository restaurantes;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired private CidadeService cidadeService;
	
	@Autowired private FormaPagamentoService formaPagamentoService;
	
	@Autowired private UsuarioService usuarioService;
	
	public List<Restaurante> todosRestaurantes(){
		return this.restaurantes.findAll();
	}

	public Restaurante buscarOuFalhar(long id) {
	return restaurantes.findById(id).orElseThrow(()->
		new RestauranteNaoEncontradoException("Não Existe Rastaurante com o código "+id));
	}
	
	@Transactional
	public Restaurante cadatrar(Restaurante restaurante) {
	
	       //adicionar a cidade (buscar a cidade pelo seu id)
			var cidade= cidadeService.buscarPorIdOuFalhar(restaurante.getEndereco().getCidade().getId());
			restaurante.getEndereco().setCidade(cidade);
			
			//adicionar a cozinha
    		Cozinha cozinha=cozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());
	        restaurante.setCozinha(cozinha);	
	
	         return restaurantes.save(restaurante);	
	}

	@Transactional
	public Restaurante actualizar(Restaurante novoRestaurante, long id) {
      Cozinha cozinha= cozinhaService.buscarOuFalhar(novoRestaurante.getCozinha().getId());
     novoRestaurante.setCozinha(cozinha);
		return restaurantes.save(novoRestaurante);
	}
	
	public void remover(long id) {
		try {
         restaurantes.delete(buscarOuFalhar(id));//no working
		}catch(EntityNotFoundException e) {
			throw new RestauranteNaoEncontradoException("Não existe Restaurante com id :"+id);
		}
	}
	
	@Transactional
	public void adicionarResponsavel(long restauranteId,long usuarioId) {
		buscarOuFalhar(restauranteId).adicionarResponsavel(
				usuarioService.buscarPorIdOuFalhar(usuarioId)
				);
	}
	@Transactional
	public void removerResponsavel(long restauranteId,long usuarioId) {
		buscarOuFalhar(restauranteId).removerResponsavel(
				usuarioService.buscarPorIdOuFalhar(usuarioId)
				);
	}
	
	@Transactional
	public void associarFormaPagamento(long restauranteId,long formaPagamentoId) {
		var formaPagamento=formaPagamentoService.buscarPorIdOuFalhar(formaPagamentoId);
		buscarOuFalhar(restauranteId).adicionarFormaPagamento(
				formaPagamento);
	}
	@Transactional
	public void desassociarFormaPagamneto(long restauranteId, long formaPagamentoId) {
		var formaPagamento= formaPagamentoService.buscarPorIdOuFalhar(formaPagamentoId);
		buscarOuFalhar(restauranteId).removerFormaPagamento(formaPagamento);
	}
	@Transactional
	public void abrirRestaurante(long id) {
		buscarOuFalhar(id).abrir();
	}
	@Transactional
	public void fecharRestaurante(long id) {
		buscarOuFalhar(id).fechar();;
	}
	@Transactional
	public void ativarRestaurante(long id) {
		buscarOuFalhar(id).ativar();
	}
	@Transactional
	public void desativarRestaurante(long id) {
		buscarOuFalhar(id).desativar();
	}

}
