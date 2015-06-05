package com.dvp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioPermissaoId implements Serializable{

	private String email;
	private String permissao;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
