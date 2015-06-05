package com.dvp.dao;

import java.util.List;

import org.hibernate.Session;

import com.dvp.model.UsuarioPermissao;

/**
 * CLasse que implementa as operacoes com banco de dados
 * @author GuilhermeP
 *
 */
class UsuarioPermissaoDAOImpl implements UsuarioPermissaoDAO {

	/**
	 * Variavel que faz as operacoes no banco de dados
	 */
	private Session session;
	
	/**
	 * O construtor precisa receber a session do hibernate para as operacoes<br/>
	 * Seguindo a injecao de dependencias, o DAO nao é responsavel pela criacao desse objeto<br/>
	 * Vem da classe {@link DAOFactory}<br/>
	 * @param session
	 */
	public UsuarioPermissaoDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(UsuarioPermissao perm) {
		this.session.beginTransaction();
		this.session.saveOrUpdate(perm);
		this.session.getTransaction().commit();
	}

	@Override
	public void remover(UsuarioPermissao perm) {
		this.session.beginTransaction();
		this.session.delete(perm);
		this.session.getTransaction().commit();
	}

	@Override
	public UsuarioPermissao recuperar(String email) {
		return null;
	}

	@Override
	public List<UsuarioPermissao> listar() {
		return null;
	}

}
