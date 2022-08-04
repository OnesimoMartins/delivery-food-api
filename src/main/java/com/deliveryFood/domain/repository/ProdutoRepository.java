package com.deliveryFood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryFood.Entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
