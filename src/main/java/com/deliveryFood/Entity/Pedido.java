package com.deliveryFood.Entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.deliveryFood.Enum.StatusPedido;

import lombok.Data;

@Data
@Entity
public class Pedido {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String codigo;
	
	private BigDecimal taxaEntrega;
	private BigDecimal valorTotal;
	private BigDecimal subTotal;
	private OffsetDateTime dataComfirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;

	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario cliente;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<ItemPedido> itens;
	
	@ManyToOne
	private FormaPagamento formaPagamento;

}
