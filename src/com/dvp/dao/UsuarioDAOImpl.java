package com.dvp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dvp.model.Usuario;

/**
 * CLasse que implementa as operacoes com banco de dados
 * @author GuilhermeP
 *
 */
class UsuarioDAOImpl implements UsuarioDAO {

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
	public UsuarioDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Usuario usuario) {
		this.session.beginTransaction();
		this.session.saveOrUpdate(usuario);
		this.session.getTransaction().commit();
	}

	@Override
	public void remover(Usuario usuario) {
		this.session.beginTransaction();
		this.session.delete(usuario);
		this.session.getTransaction().commit();
	}

	@Override
	public Usuario recuperar(int codigo) {
		// retorna um objeto do banco com base no tipo e a chave
		return (Usuario) session.get(Usuario.class, codigo);
	}

	@Override
	public List<Usuario> listar() {
		// cria uma query para listar os objetos aluno
		return session.createQuery("from Usuario").list();
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		
		Query query = session.createQuery("from Usuario u where u.email = :email");
		query.setParameter("email", login);
		return (Usuario) query.uniqueResult();
	}

}