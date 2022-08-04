package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deliveryFood.Entity.Cidade;
import com.deliveryFood.Entity.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	private String bairro;
	@NotBlank
	private String rua;
	@NotBlank
	private String municipio;
	@NotNull
	private long cidadeId;
		
	public Endereco toEndereco() {
	var endereco= new Endereco();
	var cidade=new Cidade();
	cidade.setId(cidadeId);
	endereco.setCidade(cidade);
	endereco.setBairro(bairro);
	endereco.setRua(rua);
	endereco.setMunicipio(municipio);
	return endereco;
	}
}
