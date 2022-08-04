package com.deliveryFood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class FluxoPedidoController {

	@Autowired private PedidoService pedidoService;
	
	@PutMapping("{codigo}/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarPedido( @PathVariable String codigo) {
		pedidoService.cancelarPedido(codigo);
	}
}
