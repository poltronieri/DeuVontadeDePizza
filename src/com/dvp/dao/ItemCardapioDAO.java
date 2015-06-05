package com.dvp.dao;

import java.util.List;

import com.dvp.model.Cardapio;
import com.dvp.model.ItemCardapio;

public interface ItemCardapioDAO {

	public void salvar(ItemCardapio item);
	public void remover(ItemCardapio item);
	public void removerTodos(Cardapio cardapio);
	public ItemCardapio recuperar(int codigo);
	public List<ItemCardapio> listar(Cardapio cardapio);
	public List<ItemCardapio> listar(String filtro);
	List<ItemCardapio> listarBairro(String filtro);
	List<ItemCardapio> listarCidade(String filtro);
	List<ItemCardapio> listarNomePizza(String filtro);
	List<ItemCardapio> listarNomeEstabelecimento(String filtro);
	
}
