package com.deliveryFood.api.exceptionhandler;

import org.springframework.http.HttpStatus;

public enum ProblemaType {

	RECURSO_NAO_ENCONTRADO("Recurso não encontrado","recurso-nao-encontrado",HttpStatus.NOT_FOUND),
	ENTIDADE_EM_USO("Entidade em uso","entidade-em-uso",HttpStatus.CONFLICT),
	FALHA_NA_REQUSIÇÃO("Falha na requisição","erro-de-requisicao",HttpStatus.BAD_REQUEST),
	CORPO_REQUISICAO_INVALIDO("Corpo da requisição inválido","erro-na-sintaxe",HttpStatus.BAD_REQUEST),
	ERRO_INTERNO("Erro interno da aplicação","erro-interno",HttpStatus.INTERNAL_SERVER_ERROR),
	DADOS_INVALIDOS("Dados inválidos","dados-invalidos",HttpStatus.BAD_REQUEST),
	PRODUTO_INDISPONIVEL("produto indisponível","produto-indisponivel", HttpStatus.SERVICE_UNAVAILABLE),//HttpStatus.NOT_ACCEPTABLE
	TIPO_VARIAVEL_DE_CAMINHO_INCORRETA("variável de caminho incorreta","variavel-caminho-incorrecta",HttpStatus.BAD_REQUEST)
	;
	//,ERRO_INTERNO();
	
	
	String tittle;
	 HttpStatus status;
	  String uri;
	
	ProblemaType(String tittle,String path,HttpStatus status){
		
		this.uri="Http://localHost:8080/DeliveryFood/"+path;
		this.tittle=tittle;
		this.status=status;
	}
}
