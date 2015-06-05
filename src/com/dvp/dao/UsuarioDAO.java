package com.dvp.dao;

import java.util.List;

import com.dvp.model.Usuario;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);
	public void remover(Usuario usuario);
	public Usuario recuperar(int codigo);
	public List<Usuario> listar();
	public Usuario buscarPorLogin(String login);
	
}
