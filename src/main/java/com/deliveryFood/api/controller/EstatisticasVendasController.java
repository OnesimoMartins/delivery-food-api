package com.deliveryFood.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.api.DTO.model.VendaDiaria;
import com.deliveryFood.domain.filter.VendaDiariaFilter;
import com.deliveryFood.domain.service.VendaEstatisticaService;

@RestController
@RequestMapping("estatisticas")
public class EstatisticasVendasController {

	@Autowired VendaEstatisticaService service;
	
	@GetMapping(path = "/venda-diaria")// 
	public List<VendaDiaria> consultarVendasDiaria(@RequestBody VendaDiariaFilter filter) {

		filter.getDataInicio().datesUntil(filter.getDataFim()).forEach(System.out::println);
		service.consultarVendasPorDia(filter);	
		return null;
	}
}
