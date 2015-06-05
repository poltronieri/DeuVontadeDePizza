package com.dvp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cardapio")
public class Cardapio {

	@Id
	@GeneratedValue
	@Column(name = "codigo_cardapio")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "codigo_endereco")
	private UsuarioEndereco endereco;

	@Column(name = "nome_cardapio")
	private String nomeCardapio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public UsuarioEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(UsuarioEndereco endereco) {
		this.endereco = endereco;
	}

	public String getNomeCardapio() {
		return nomeCardapio;
	}

	public void setNomeCardapio(String nomeCardapio) {
		this.nomeCardapio = nomeCardapio;
	}

}
