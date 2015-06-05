package com.dvp.dao;

import java.util.List;

import com.dvp.model.UsuarioPermissao;

public interface UsuarioPermissaoDAO {

	public void salvar(UsuarioPermissao perm);
	public void remover(UsuarioPermissao perm);
	public UsuarioPermissao recuperar(String email);
	public List<UsuarioPermissao> listar();
	
}
