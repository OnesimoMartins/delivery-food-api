package com.deliveryFood.api.DTO.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VendaDiaria {

	LocalDate data;
	BigDecimal totalFaturado;
	int totalVendas;
}
