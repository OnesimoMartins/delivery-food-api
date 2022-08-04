package com.deliveryFood.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.deliveryFood.api.DTO.response.CozinhaResponse;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Cozinha {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes;

	public static List<CozinhaResponse> toCozinhaResponseList(List<Cozinha> cozinhas) {
		var cozinhasResponse=new ArrayList<CozinhaResponse>();
		cozinhas.forEach(x->cozinhasResponse.add(new CozinhaResponse(x)));
		return cozinhasResponse;
	}
}
