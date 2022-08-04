package com.deliveryFood.domain.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter@ToString
public class VendaDiariaFilter {

	private Long restauranteId;	
	private LocalDate dataInicio;
	private LocalDate dataFim;
}
