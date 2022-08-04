package com.deliveryFood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryFood.Entity.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long>  {

}
