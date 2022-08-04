package com.deliveryFood.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.deliveryFood.Entity.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputUpdate {

	@NotBlank
	private String nome;
	
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
	, message = "Email inválido")
	private String email;
	
	@NotBlank
	@Size(min = 8,max = 15)
	private String senha;
	
	public Usuario toUsuario() {
		var usuario=new Usuario();
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.setNome(nome);
		return usuario;
	}

}
