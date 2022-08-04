package com.deliveryFood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryFood.Entity.Pedido;

@Repository
public interface PedidoRepositoy extends JpaRepository<Pedido, Long> {

	 Optional<Pedido> findPedidoByCodigo(String value); 
}
