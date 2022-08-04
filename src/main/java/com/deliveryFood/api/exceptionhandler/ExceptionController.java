//package com.deliveryFood.api.exceptionhandler;
//
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import com.deliveryFood.api.DTO.response.ProdutoResponse;
//import com.deliveryFood.domain.exception.EmailAssociadoAOutroUsuarioException;
//import com.deliveryFood.domain.exception.EntidadeEmUsoException;
//import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
//import com.deliveryFood.domain.exception.NegocioException;
//import com.deliveryFood.domain.exception.RestauranteSemProdutoException;
//import com.fasterxml.jackson.databind.JsonMappingException.Reference;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import com.fasterxml.jackson.databind.exc.PropertyBindingException;
//
//@ControllerAdvice
//public class ExceptionController extends ResponseEntityExceptionHandler {
//
//	@Autowired
//	private MessageSource messageSource;
//
//	@Override
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		ProblemaType type = ProblemaType.RECURSO_NAO_ENCONTRADO;
//
//		String str = "O recusro '" + ex.getHttpMethod() + ":" + ex.getRequestURL() + "' é inexitente ";
//		Problema problema = problemaCreator(type, str).build();
//
//		return this.handleExceptionInternal(ex, problema, headers, status, request);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest req) {
//
//		ProblemaType type = ProblemaType.DADOS_INVALIDOS;
//
//		List<Problema.Campo> camposComErro = new ArrayList<>();
//
//		ex.getBindingResult().getFieldErrors().forEach(x -> {
//			camposComErro.add(
//					new Problema.Campo(x.getField(), messageSource.getMessage(x, LocaleContextHolder.getLocale())));
//		});
//
//		Problema problema = problemaCreator(type, "dados inválidos inseridos").campos(camposComErro).build();
//
//		return handleExceptionInternal(ex, problema, new HttpHeaders(), type.status, req);
//	}
//
//	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
//	public ResponseEntity<Object> handlMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
//			WebRequest req) {
//
//		final String MSG_ERRO = "O parâmetro '" + e.getName() + "' recebeu o valor '" + e.getValue()
//				+ "' que é um tipo inválido, corrija e informe um valor do tipo Long.";
//
//		ProblemaType type = ProblemaType.TIPO_VARIAVEL_DE_CAMINHO_INCORRETA;
//		Problema problema = problemaCreator(type, MSG_ERRO).build();
//
//		return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		Throwable rootCause = ExceptionUtils.getRootCause(ex);
//		ProblemaType type = ProblemaType.CORPO_REQUISICAO_INVALIDO;
//
//		if (rootCause instanceof PropertyBindingException)
//			return handlePropertyBindindgException((PropertyBindingException) rootCause, headers, status, request);
//
//		else if (rootCause instanceof InvalidFormatException)
//			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
//
//		Problema problema = problemaCreator(type, "O corpo da requisição está incorrecto, corrija-o. ").build();
//		return handleExceptionInternal(ex, problema, new HttpHeaders(), type.status, request);
//	}
//
//	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException rootCause, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		String path = unirObjecto(rootCause.getPath());
//
//		String detail = "A propriedade '" + path + "' deve ser do tipo " + rootCause.getTargetType().getSimpleName();
//
//		ProblemaType type = ProblemaType.CORPO_REQUISICAO_INVALIDO;
//		Problema problema = problemaCreator(type, detail).build();
//		return this.handleExceptionInternal(rootCause, problema, new HttpHeaders(), status, request);
//	}
//
//	private ResponseEntity<Object> handlePropertyBindindgException(PropertyBindingException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//
//		String path = unirObjecto(ex.getPath());
//
//		final String detail = "O campo " + path + " não existe, elimine ou retifique-o ";
//		ProblemaType type = ProblemaType.CORPO_REQUISICAO_INVALIDO;
//		Problema problema = problemaCreator(type, detail).build();
//		return this.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
//	}
//
//	ResponseEntity<Object> handleBindingException(PropertyBindingException rootCause, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//
//		String path = unirObjecto(rootCause.getPath());
//		final String detail = "O campo " + path + " não existe nesse Objecto";
//		ProblemaType type = ProblemaType.CORPO_REQUISICAO_INVALIDO;
//		Problema problema = problemaCreator(type, detail).build();
//		return this.handleExceptionInternal(rootCause, problema, new HttpHeaders(), status, request);
//
//	}
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> erroIternoEception(Exception e, WebRequest req) {
//
//		final String MSG_ERRO = "Erro interno da aplicação, tente novamente e se o erro persistir "
//				+ "contacte o administrador do sistema.";
//
//		ProblemaType type = ProblemaType.ERRO_INTERNO;
//		Problema problema = problemaCreator(type, MSG_ERRO).build();
//
//		return handleExceptionInternal(e, problema, new HttpHeaders(), type.status, req);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//
//		if (body == null) {
//			ProblemaType type = ProblemaType.FALHA_NA_REQUSIÇÃO;
//			Problema problema = problemaCreator(type, ex.getMessage()).build();
//			return super.handleExceptionInternal(ex, problema, headers, status, request);
//		}
//
//		return super.handleExceptionInternal(ex, body, headers, status, request);
//	}
//
//	@ExceptionHandler(EntidadeNaoEncontradaException.class)
//	ResponseEntity<Object> EntidadeNaoEncontradaExceptionHandler(EntidadeNaoEncontradaException e, WebRequest req) {
//		ProblemaType type = ProblemaType.RECURSO_NAO_ENCONTRADO;
//		Problema problema = problemaCreator(type, e.getMessage()).build();
//		return this.handleExceptionInternal(e, problema, new HttpHeaders(), type.status, req);
//	}
//
//	@ExceptionHandler(NegocioException.class)
//	public ResponseEntity<Object> NegocioExceptionHandler(NegocioException e, WebRequest req) {
//
//		ProblemaType type = ProblemaType.FALHA_NA_REQUSIÇÃO;
//		Problema problema = problemaCreator(type, e.getMessage()).build();
//
//		return this.handleExceptionInternal(e, problema, new HttpHeaders(), type.status, req);
//	}
//
//	@ExceptionHandler(EntidadeEmUsoException.class)
//	public ResponseEntity<Object> EntidadeEmUsoExceptionHandler(EntidadeEmUsoException e, WebRequest req) {
//		// WebUtils.
//		// CollectionUtils.
//		ProblemaType type = ProblemaType.ENTIDADE_EM_USO;
//		Problema problema = problemaCreator(type, e.getMessage()).build();
//
//		return this.handleExceptionInternal(e, problema, new HttpHeaders(), type.status, req);
//	}
//
//	@ExceptionHandler(EmailAssociadoAOutroUsuarioException.class)
//	public ResponseEntity<Object> EmailAssociadoAOutroUsuarioExceptionHandler(EmailAssociadoAOutroUsuarioException ex) {
//
//		ProblemaType type = ProblemaType.DADOS_INVALIDOS;
//
//		List<Problema.Campo> camposComErro = new ArrayList<>();
//
//	   camposComErro.add( new Problema.Campo("email",ex.getMessage()) );
//
//		Problema problema = problemaCreator(type, "dados inválidos inseridos").campos(camposComErro).build();
//
//		return  ResponseEntity.badRequest().body(problema);
//	}
//	
//	@ExceptionHandler(RestauranteSemProdutoException.class)
//	public ResponseEntity<Object> faltaDeProdutoExceptionHandler(RestauranteSemProdutoException ex) {
//
//		ProblemaType type = ProblemaType.PRODUTO_INDISPONIVEL;
//		
//		ex.getProdutosEmFalta();	
//		
//		Map<String,List<ProdutoResponse>> produtosEmFalta=new HashMap<>();
//		produtosEmFalta.put("produtos em falta", ex.getProdutosEmFalta());
//		
//		Problema problema = problemaCreator(type, "produto indisponível neste restaurante").produtosEmFalta(
//				produtosEmFalta
//				).build();
//
//		return  ResponseEntity.badRequest().body(problema);
//	}
//	
//	// permite unir as referencias de um objecto em uma string ex='cozinha.id'
//	private String unirObjecto(List<Reference> ref) {
//		return ref.stream().map(x -> x.getFieldName()).collect(Collectors.joining("."));
//	}
//
//	protected Problema.ProblemaBuilder problemaCreator(ProblemaType type, String detail) {
//		return Problema.builder().detail(detail).status((short) type.status.value()).timeStamp(OffsetDateTime.now())
//				.tittle(type.tittle).type(type.uri);
//	}
//}
