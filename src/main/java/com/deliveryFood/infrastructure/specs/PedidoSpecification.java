package com.deliveryFood.infrastructure.specs;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@ToString
public class PedidoSpecification {
	@Id
	private Long id;
	private LocalDate dataCriacao;
	private BigDecimal valorTotal;

}
