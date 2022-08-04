package com.deliveryFood.infrastructure.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deliveryFood.Entity.Pedido;
import com.deliveryFood.api.DTO.model.VendaDiaria;
import com.deliveryFood.domain.filter.VendaDiariaFilter;
import com.deliveryFood.domain.service.RestauranteService;
import com.deliveryFood.domain.service.VendaEstatisticaService;
import com.deliveryFood.infrastructure.specs.PedidoSpecification;

@Repository
public class VendaDiariaEstatisticaServiceImpl implements VendaEstatisticaService {

	@Autowired
	private RestauranteService restauranteService;
	@Autowired
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasPorDia(VendaDiariaFilter filter) {

		StringBuilder queryPedidos = new StringBuilder(""
				+ "SELECT p.id,p.data_criacao,p.valor_total "
				+ " from pedido p WHERE 0=0 ");

		if (filter.getRestauranteId() != null) {
			restauranteService.buscarOuFalhar(filter.getRestauranteId());
			queryPedidos.append("AND p.restaurante_id=" + filter.getRestauranteId()+" ");
		}

		if(filter.getDataInicio() != null) {
		    queryPedidos.append(" AND p.data_criacao>='" + filter.getDataInicio() + "'"); 
		}else {

			filter.setDataInicio(LocalDate.of(2010, 12, 1));
		  System.out.println("\n\nDEBUG:"+filter.getDataInicio());
		}
		
		

		if (filter.getDataFim() != null) {
			queryPedidos.append("AND p.data_criacao<= '" + filter.getDataFim() + "'");
		}else {
			filter.setDataFim(LocalDate.now());
		 }
		
		queryPedidos.append(" AND p.status_pedido IN ('COMFIRMADO','ENTREGUE') ");

		List<PedidoSpecification> pedidosFiltrados =
				manager.createNativeQuery(queryPedidos.toString(), PedidoSpecification.class)
				.getResultList();
		
		pedidosFiltrados.stream().map(PedidoSpecification::toString).forEach(System.out::println);

		List<VendaDiaria> vendasDiarias=new ArrayList<>();
		
//		var datas=filter.getDataInicio().datesUntil(filter.getDataFim());
//		datas.collect(Collectors.toList()).add(filter.getDataFim());
		
	filter.getDataInicio().datesUntil(filter.getDataFim()).forEach(
				dataActual->{
					
	     		var qtVendas=0;
		    	List<BigDecimal> totalCadaPedido=new ArrayList<>();
					
				pedidosFiltrados.stream().filter(pedido-> pedido.getDataCriacao().equals(dataActual))
				
					    .collect(Collectors.toList())
					    .forEach(
					    	p->{
					    		qtVendas!=null;//++;
					    		totalCadaPedido.add(p.getValorTotal());
					    		});
				
			  if(qtVendas>0) {
				 var n= new VendaDiaria(dataActual,
						  calcularValorPedidoMesmaData(totalCadaPedido),qtVendas);//teste
				  
				  vendasDiarias.add(new VendaDiaria(dataActual,
						  calcularValorPedidoMesmaData(totalCadaPedido),qtVendas));
			      
				  System.out.println(n.toString());
			         }
				});
		
		return null;

	}
	private BigDecimal calcularValorPedidoMesmaData(List<BigDecimal> pedidos) {
		return pedidos.stream()
	    .reduce(BigDecimal.ZERO,BigDecimal::add);
	}

}
