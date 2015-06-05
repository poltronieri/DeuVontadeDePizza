package com.dvp.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dvp.dao.CardapioDAO;
import com.dvp.dao.DAOFactory;
import com.dvp.dao.ItemCardapioDAO;
import com.dvp.model.Cardapio;
import com.dvp.model.ItemCardapio;
import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;

@ManagedBean(name="cardapioBean")
@ViewScoped
public class CardapioBean {	

	private Usuario usuario;
	
	private Cardapio cardapio;
	
	private List<String> enderecos;
	private String enderecoSelecionado;
	
	private List<ItemCardapio> produtos;
	private ItemCardapio produtoAtual;
	
	@PostConstruct
	private void inicializar(){
		usuario = new UsuarioLogadoBean().getUsuarioLogado();
		enderecos = new ArrayList<String>();
		for(UsuarioEndereco end : DAOFactory.getUsuarioEnderecoDAO().listar(usuario.getCodigo())){
			enderecos.add(end.toString());
		}
		enderecoSelecionado = null;
		produtoAtual = new ItemCardapio();
		produtos = new ArrayList<ItemCardapio>();
		cardapio = new Cardapio();
	}
	
	public String adicionar(){
		produtos.add(produtoAtual);
		produtoAtual = new ItemCardapio();
		return null;
	}
	
	public String carregarCardapio(){
		UsuarioEndereco endObj = null;
		for(UsuarioEndereco end : DAOFactory.getUsuarioEnderecoDAO().listar(usuario.getCodigo())){
			if(end.toString().equals(enderecoSelecionado)){
				endObj = end;
			}
		}
		CardapioDAO cDao = DAOFactory.getCardapioDAO();
		ItemCardapioDAO iDao = DAOFactory.getItemCardapioDAO();
		cardapio = cDao.recuperar(endObj);
		if(cardapio != null){
			produtos = iDao.listar(cardapio);
		}else{
			cardapio = new Cardapio();
			produtos = new ArrayList<ItemCardapio>();
		}
		return null;
	}
	
	public String salvar(){
		UsuarioEndereco endObj = null;
		for(UsuarioEndereco end : DAOFactory.getUsuarioEnderecoDAO().listar(usuario.getCodigo())){
			if(end.toString().equals(enderecoSelecionado)){
				endObj = end;
			}
		}
		cardapio.setEndereco(endObj);
		CardapioDAO cDao = DAOFactory.getCardapioDAO();
		ItemCardapioDAO iDao = DAOFactory.getItemCardapioDAO();
		cardapio = cDao.salvar(cardapio);
		iDao.removerTodos(cardapio);
		for (ItemCardapio item : produtos) {
			item.setCardapio(cardapio);
			iDao.salvar(item);
		}
		enderecoSelecionado = null;
		produtoAtual = new ItemCardapio();
		produtos = new ArrayList<ItemCardapio>();
		cardapio = new Cardapio();
		return null;
	}
	
	public void removerDoGrid(ItemCardapio produto){
		produtos.remove(produto);
	}

	public List<String> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<String> enderecos) {
		this.enderecos = enderecos;
	}

	public String getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(String enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public List<ItemCardapio> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemCardapio> produtos) {
		this.produtos = produtos;
	}

	public ItemCardapio getProdutoAtual() {
		return produtoAtual;
	}

	public void setProdutoAtual(ItemCardapio produtoAtual) {
		this.produtoAtual = produtoAtual;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
	
}
