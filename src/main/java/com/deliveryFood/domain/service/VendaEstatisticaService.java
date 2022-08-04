package com.deliveryFood.domain.service;

import java.util.List;

import com.deliveryFood.api.DTO.model.VendaDiaria;
import com.deliveryFood.domain.filter.VendaDiariaFilter;

public interface VendaEstatisticaService {
	public List<VendaDiaria> consultarVendasPorDia(VendaDiariaFilter filter);
}
