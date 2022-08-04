package com.deliveryFood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deliveryFood.Entity.Cozinha;
import com.deliveryFood.domain.exception.EntidadeEmUsoException;
import com.deliveryFood.domain.exception.EntidadeNaoEncontradaException;
import com.deliveryFood.domain.service.CozinhaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CozinhaTests {

	@LocalServerPort
	int porta;
	
	@Autowired
	private CozinhaService cozinhaService;

	//@Test
	void deveRetornarStatusOkE4Cozinhas() {
		RestAssured.given().basePath("/cozinhas").port(porta)
		.contentType(ContentType.JSON).when().get().then()
				.statusCode(200)
				.body("", Matchers.hasSize(4))
				.body("nome", Matchers.hasItem("Chinesa"));
	}
	//@Test
	void deveRetornarStatusOkQuandoConsultar() {

		RestAssured.given().basePath("/cozinhas")
		.port(porta)
		.accept(ContentType.JSON)
		.pathParam("id", 1)
	.when().get("/{id}")
	.then().statusCode(200);
	}
	@Test
	void deveRetornar201QuandoCadastrarCozinha() {
	
		var cozinhaJSON="{ \"nome\":\"Indiana\"}" ;
		System.out.println(cozinhaJSON);
		RestAssured.given().basePath("/cozinhas").accept(ContentType.JSON).port(porta)
		.body(cozinhaJSON)
		.when().post()
		.then().statusCode(201);
	}

	// @Test
	void deveCadastrarCozinha() {

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("italiana");
		cozinha = cozinhaService.inserir(cozinha);
		assertThat(cozinha).isNotNull();
	}

	// @Test
	void deveFalharAoCadastrarCozinha() {
		assertThrows(ConstraintViolationException.class, () -> {

			Cozinha cozinha = new Cozinha();
			cozinha = cozinhaService.inserir(cozinha);
		});

		// assertThat(cozinha).isNotNull();
	}

	// @Test
	void deveFalharAoEliminarCozinhaEmUso() {
		assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.eliminar(1));

	}

	// @Test
	void deveFalharAoEliminarCozinhaInexistente() {

		assertThrows(EntidadeNaoEncontradaException.class, () -> cozinhaService.eliminar(100));
	}
}
