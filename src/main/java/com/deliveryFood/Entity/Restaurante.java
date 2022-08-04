package com.deliveryFood.Entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Restaurante {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	@Column(name = "taxa_entrega")
	private BigDecimal taxaEntrega;
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	
	@UpdateTimestamp
	private OffsetDateTime ultimaActualizacao;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurante_produtos",joinColumns = @JoinColumn(name="restaurante_id")
	,inverseJoinColumns = @JoinColumn(name="produtos_id"))
	private List<Produto> produtos;
	
	@ManyToOne
	private Cozinha cozinha;
	
	private Boolean aberto=Boolean.FALSE;
	private Boolean ativo=Boolean.TRUE;
//	@Override
//	public String toString() {
//		return null;
//		}
//	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="restaurante_forma_pagamento",
	joinColumns = @JoinColumn(name="restaurante_id"),
	inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento;
	
	@Setter(value = AccessLevel.NONE)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurante_usuario_responsavel",
	joinColumns = @JoinColumn(name="restaurante_id"),
	inverseJoinColumns = @JoinColumn(name="usuario_id"))
	private Set<Usuario> responsaveis;
	
	//ad produtos
	public Restaurante(@Positive long restauranteId) {
	this.id=restauranteId;
	}
	
	public boolean adicionarResponsavel(Usuario usuario) {
		return this.responsaveis.add(usuario);
	}
	public boolean removerResponsavel(Usuario usuario) {
		return this.responsaveis.remove(usuario);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return this.formasPagamento.add(formaPagamento);
	}
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return this.getFormasPagamento().remove(formaPagamento);
	}
	public boolean consultarSeAceitaFomaDePagamento(FormaPagamento formaPagamento) {
		return this.formasPagamento.contains(formaPagamento);
	}
	
	public boolean consultarProduto(Produto produto) {
		return this.produtos.contains(produto);
	}

	public void fechar() {this.setAberto(false);}
	public void abrir() {this.setAberto(true);}
	
	public void ativar() {this.setAtivo(true);}
	public void desativar() {this.setAtivo(false);}
	
}
