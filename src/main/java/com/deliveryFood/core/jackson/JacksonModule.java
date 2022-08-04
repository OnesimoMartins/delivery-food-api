package com.deliveryFood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	 public JacksonModule() {
	//	 setMixInAnnotation(Restaurante.class,RestauranteMixin.class);
		// setMixInAnnotation(Cidade.class, CidadeMixin.class);
     }
}
