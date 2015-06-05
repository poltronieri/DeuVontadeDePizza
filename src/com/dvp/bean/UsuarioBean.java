package com.dvp.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dvp.dao.DAOFactory;
import com.dvp.dao.UsuarioDAO;
import com.dvp.dao.UsuarioEnderecoDAO;
import com.dvp.dao.UsuarioPermissaoDAO;
import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;
import com.dvp.model.UsuarioPermissao;
import com.dvp.model.UsuarioPermissaoId;

@ManagedBean
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private UsuarioEndereco usuarioEndereco = new UsuarioEndereco();
	private List<UsuarioEndereco> enderecosLista = new ArrayList<UsuarioEndereco>();
	private List<String> estados = new ArrayList<>();
	
	private List<UsuarioEndereco> paraRemover = new ArrayList<UsuarioEndereco>();
	
	@PostConstruct
	public void inicializa(){
		System.out.println("INICIALIZA!!!");
		setEstados(listaEstados());		
		
		UsuarioLogadoBean usuarioLogadoBean = new UsuarioLogadoBean();
		Usuario usuarioLogado = usuarioLogadoBean.getUsuarioLogado();
		if(usuarioLogado != null){
			usuario = usuarioLogado;
			UsuarioEnderecoDAO daoEndereco = DAOFactory.getUsuarioEnderecoDAO();
			enderecosLista = daoEndereco.listar(usuarioLogado.getCodigo());
			
		}
	}
	
	public List<String> listaEstados(){
		List<String> estados = new ArrayList<>();
		
		estados.add("AC");
		estados.add("AL");
		estados.add("AP");
		estados.add("AM");
		estados.add("BA");
		estados.add("CE");
		estados.add("DF");
		estados.add("ES");
		estados.add("GO");
		estados.add("MA");
		estados.add("MT");
		estados.add("MS");
		estados.add("MG");
		estados.add("PA");
		estados.add("PB");
		estados.add("PR");
		estados.add("PE");
		estados.add("PI");
		estados.add("RJ");
		estados.add("RN");
		estados.add("RS");
		estados.add("RO");
		estados.add("RR");
		estados.add("SC");
		estados.add("SP");
		estados.add("SE");
		estados.add("TO");
		
		return estados;
	}
	
	public String addNaGrid(){
		enderecosLista.add(usuarioEndereco);
		paraRemover.remove(usuarioEndereco);
		usuarioEndereco = new UsuarioEndereco();
		return null;
	}
	
	public void removerDoGrid(UsuarioEndereco end){
		paraRemover.add(end);
		enderecosLista.remove(end);
	}
	
	public String salvar(){
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		UsuarioEnderecoDAO usuarioEnderecoDAO = DAOFactory.getUsuarioEnderecoDAO();
		UsuarioPermissaoDAO permissaoDAO = DAOFactory.getUsuarioPermissaoDAO();
		
		usuarioDAO.salvar(usuario);
		
		usuarioEnderecoDAO.removerVarios(paraRemover);
		
		for (UsuarioEndereco usuarioEndereco : enderecosLista) {
			usuarioEndereco.setUsuario(usuario);
			usuarioEnderecoDAO.salvar(usuarioEndereco);
		}
		
		UsuarioPermissao perm = new UsuarioPermissao();
		UsuarioPermissaoId permId = new UsuarioPermissaoId();
		permId.setEmail(usuario.getEmail());
		permId.setPermissao("ROLE_USUARIO");
		perm.setId(permId);
		
		permissaoDAO.salvar(perm);
		
		paraRemover.clear();
		
		return "/restrito/painelControle.jsf?faces-redirect=true";
	}
	
	public boolean temCardapio(UsuarioEndereco end){
		try{
			return DAOFactory.getCardapioDAO().recuperar(end) != null;
		}catch(Exception e){
			return false;
		}
	}
	
	public String criptografaSenha(String senha){
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return the usuarioEndereco
	 */
	public UsuarioEndereco getUsuarioEndereco() {
		return usuarioEndereco;
	}

	/**
	 * @return the enderecosLista
	 */
	public List<UsuarioEndereco> getEnderecosLista() {
		return enderecosLista;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param usuarioEndereco the usuarioEndereco to set
	 */
	public void setUsuarioEndereco(UsuarioEndereco usuarioEndereco) {
		this.usuarioEndereco = usuarioEndereco;
	}

	/**
	 * @param enderecosLista the enderecosLista to set
	 */
	public void setEnderecosLista(List<UsuarioEndereco> enderecosLista) {
		this.enderecosLista = enderecosLista;
	}

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}


}
