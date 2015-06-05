package com.dvp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dvp.dao.DAOFactory;
import com.dvp.dao.UsuarioDAO;
import com.dvp.dao.UsuarioEnderecoDAO;
import com.dvp.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioLogadoBean {

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exContext = context.getExternalContext();
		String login = exContext.getRemoteUser();
		if (this.usuarioLogado == null
				|| !login.equals(this.usuarioLogado.getEmail())) {
			if (login != null) {
				UsuarioDAO dao = DAOFactory.getUsuarioDAO();
				this.usuarioLogado = dao.buscarPorLogin(login);
			}
		}
		return usuarioLogado;
	}
	
	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exContext = context.getExternalContext();
		
		exContext.invalidateSession();
		
		return "/index.jsf?faces-redirect=true";
	}
}
