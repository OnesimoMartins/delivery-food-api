package com.deliveryFood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import com.deliveryFood.api.DTO.response.ProdutoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//@JsonPOJOBuilder
public class Problema {

	private OffsetDateTime timeStamp;
    private String tittle;
	private String detail;
	private short status;
	private String type;
	
	@JsonInclude(Include.NON_NULL)
	private List<Campo> campos;
	
	@JsonInclude(Include.NON_NULL)
	Map<String,List<ProdutoResponse>> produtosEmFalta;
	
	@Getter
	@AllArgsConstructor
	static class Campo{
		final private String nome;
		final private String mensagem;
	}
	
	
}
