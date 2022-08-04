package com.deliveryFood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.deliveryFood.Entity.Restaurante;
import com.deliveryFood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> consultarRestaurantesLike(String nome) {
		String jpql = "from Restaurante where nome like :nome ";// usando jpql
		return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%" + nome + "%").getResultList();
	}

	public List<Restaurante> consultarPorNomeAndTaxaEntrega(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		StringBuilder jpql = new StringBuilder("from Restaurante where 0=0");

		Map<String, Object> parametros = new HashMap<>();

		if (StringUtils.hasLength(nome)) {
			System.out.println("nome=" + nome);
			jpql.append(" and nome like :nome");
			parametros.put("nome", "%" + nome + "%");
		}

		if (taxaInicial != null) {
			jpql.append(" and taxa_entrega >= :taxaInicial ");
			System.out.println("taxa inicial=" + taxaInicial);
			parametros.put("taxaInicial", taxaInicial);
		}

		if (taxaFinal != null) {
			jpql.append(" and taxa_entrega <= :taxaFinal ");
			System.out.println("taxa final=" + taxaFinal);
			parametros.put("taxaFinal", taxaFinal);
		}
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach((key, value) -> query.setParameter(key, value));
		return query.getResultList();

	}

	@Override
	public List<Restaurante> allResataurantesSQL() {
		// return manager.createNativeQuery("Select *from restaurante").getResultList();
		return manager.createNativeQuery("Select *from restaurante where nome like '%coo%'", Restaurante.class)
				.getResultList();
	}
}
