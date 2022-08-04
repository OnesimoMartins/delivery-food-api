package com.deliveryFood.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	@Column(name="endereco_bairro")
	private String bairro;
	@Column(name="endereco_rua")
	private String rua;
	@Column(name="endereco_municipio")
	private String municipio;
	@ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
	
//	@Override
//	public String toString() {
//		StringBuilder str=new StringBuilder();
//		str.append(cidade.getProvincia().getNome()+","); 
//		str.append(cidade.getNome()+",");
//		str.append(municipio+",");
//		str.append(bairro+",");
//		str.append(rua);
//		return str.toString();
//	}

}
