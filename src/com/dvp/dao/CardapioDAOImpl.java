package com.dvp.dao;

import java.util.List;

import org.hibernate.Session;

import com.dvp.model.Cardapio;
import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;

/**
 * CLasse que implementa as operacoes com banco de dados
 * @author GuilhermeP
 *
 */
class CardapioDAOImpl implements CardapioDAO {

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
	public CardapioDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public Cardapio salvar(Cardapio cardapio) {
		this.session.beginTransaction();
		this.session.saveOrUpdate(cardapio);
		this.session.getTransaction().commit();
		return cardapio;
	}
	
	@Override
	public void remover(Cardapio cardapio) {
		this.session.beginTransaction();
		this.session.delete(cardapio);
		this.session.getTransaction().commit();
	}

	@Override
	public Cardapio recuperar(UsuarioEndereco endereco) {
		return (Cardapio) session.createQuery("from Cardapio c where c.endereco = :end").setParameter("end", endereco).uniqueResult();
	}

	@Override
	public List<Cardapio> listar(Usuario usuario) {
		return session.createQuery("from Cardapio c where c.endereco.usuario = :usr").setParameter("usr", usuario).list();
	}

}
