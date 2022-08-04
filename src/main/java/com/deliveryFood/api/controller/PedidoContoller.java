package com.deliveryFood.api.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.Entity.Usuario;
import com.deliveryFood.api.DTO.input.PedidoInput;
import com.deliveryFood.api.DTO.response.PedidoResponse;
import com.deliveryFood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoContoller {

@Autowired	private PedidoService pedidoService;
	
@PostMapping
	public PedidoResponse criarPedido(@Valid @RequestBody PedidoInput pedidoInput) {
		
		var novoPedido=pedidoInput.toPedido();
        
		//TODO autenticar usuario
		var cliente=new Usuario();
		cliente.setId(1);
		novoPedido.setCliente(cliente);
		
		novoPedido.setSubTotal(BigDecimal.TEN);

	    var pedido=pedidoService.criarPedido( novoPedido);
		System.out.println("ANALISE: "+pedido);
		
		return new PedidoResponse( pedido );
	
	}
}
