package com.deliveryFood.Entity;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	@ManyToMany
	private List<Grupo> grupos;
	
	public void adicionarGrupo(Grupo grupo) {
		this.grupos.add(grupo);
	}
	public void removerGrupo(Grupo grupo) {
		this.grupos.remove(grupo);
	}
	
}
