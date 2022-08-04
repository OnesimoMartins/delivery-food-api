package com.deliveryFood.Entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(onlyExplicitlyIncluded = true)
@Table(name="grupo")
public class Grupo {

     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
	
     private String nome;
     
     @ManyToMany
     @JoinTable(name="grupo_permissao",
    		 joinColumns = @JoinColumn(name = "grupo_id"),
    		 inverseJoinColumns = @JoinColumn(name="permissao_id")
    		 )
     private Set<Permissao> permissoes;
}
