package com.dvp.dao;

import java.util.List;

import com.dvp.model.Cardapio;
import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;

public interface CardapioDAO {

	public Cardapio salvar(Cardapio cardapio);
	public void remover(Cardapio cardapio);
	public Cardapio recuperar(UsuarioEndereco endereco);
	public List<Cardapio> listar(Usuario usuario);
	
}
