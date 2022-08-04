package com.deliveryFood.api.DTO.response;

import com.deliveryFood.Entity.Cidade;
import com.deliveryFood.Entity.Endereco;

import lombok.Getter;

@Getter
public class EnderecoResponse {
final 	private String bairro;
final	private String rua;
final	private String municipio;
final	private Cidade cidade;
 

public EnderecoResponse(Endereco endereco) {
this.bairro=endereco.getBairro();
this.cidade=endereco.getCidade();
this.municipio=endereco.getMunicipio();
this.rua=endereco.getRua();
}

}
