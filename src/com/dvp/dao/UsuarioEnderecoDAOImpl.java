package com.dvp.dao;

import java.util.List;

import org.hibernate.Session;

import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;

/**
 * CLasse que implementa as operacoes com banco de dados
 * @author GuilhermeP
 *
 */
class UsuarioEnderecoDAOImpl implements UsuarioEnderecoDAO {

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
	public UsuarioEnderecoDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(UsuarioEndereco usuario) {
		this.session.beginTransaction();
		this.session.saveOrUpdate(usuario);
		this.session.getTransaction().commit();
	}

	@Override
	public void remover(UsuarioEndereco usuario) {
		this.session.beginTransaction();
		this.session.delete(usuario);
		this.session.getTransaction().commit();
	}
	
	@Override
	public void removerVarios(List<UsuarioEndereco> enderecos) {
		this.session.beginTransaction();
		for(UsuarioEndereco e : enderecos){
			this.session.delete(e);
		}
		this.session.getTransaction().commit();
//		this.session.createQuery("delete from UsuarioEndereco i where i.usuario = :usr").setParameter("usr", usuario).executeUpdate();
	}

	@Override
	public UsuarioEndereco recuperar(int codigo) {
		// retorna um objeto do banco com base no tipo e a chave
		return (UsuarioEndereco) session.get(UsuarioEndereco.class, codigo);
	}

	@Override
	public List<UsuarioEndereco> listar(int codigo) {
		// cria uma query para listar os objetos aluno
		return session.createQuery("from UsuarioEndereco u where u.usuario.codigo = :codigo").setParameter("codigo", codigo).list();
	}

}
