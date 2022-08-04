package com.deliveryFood.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produto {

	public Produto(@NotNull long produtoId) {
	this.id=produtoId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	private String nome;
	
	private boolean ativo;
   
//	@ManyToOne
//	@JoinColumn(name = "restaurante_id")
//	Restaurante restaurante;

}
