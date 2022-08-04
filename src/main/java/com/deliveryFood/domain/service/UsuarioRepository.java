package com.deliveryFood.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deliveryFood.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value="select u.email from usuario u WHERE u.email=:value",nativeQuery=true)
	public String emailExists(@Param("value")String email);
	public Usuario findByEmail(String email);
}
