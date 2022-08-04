package com.deliveryFood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deliveryFood.Entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante,Long>
,RestauranteRepositoryQueries{

	List<Restaurante> findByNome(String nome);
	List<Restaurante> findByTaxaEntregaBetween(BigDecimal inicial, BigDecimal Final);
	
	List<Restaurante> findByNomeContainingAndCozinhaNome(String nome,String cozinha);//se jpql
	
	@Query("from Restaurante where nome like %:nome% and cozinha.nome=:cozinha")
	List<Restaurante> consultarPorNome(@Param("nome")String nome,@Param("cozinha")String cozinha);//com jpql
	
	int countByCozinhaId(long id);//ainda tem= exists, top3,first...
}
