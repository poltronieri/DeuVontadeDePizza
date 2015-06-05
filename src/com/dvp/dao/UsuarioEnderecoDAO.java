package com.dvp.dao;

import java.util.List;

import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;

public interface UsuarioEnderecoDAO {

	public void salvar(UsuarioEndereco endereco);
	public void remover(UsuarioEndereco endereco);
	public void removerVarios(List<UsuarioEndereco> enderecos);
	public UsuarioEndereco recuperar(int codigo);
	public List<UsuarioEndereco> listar(int codigo);
	
}
