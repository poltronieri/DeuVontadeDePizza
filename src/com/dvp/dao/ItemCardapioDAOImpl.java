package com.dvp.dao;

import java.util.List;

import org.hibernate.Session;

import com.dvp.model.Cardapio;
import com.dvp.model.ItemCardapio;

/**
 * CLasse que implementa as operacoes com banco de dados
 * @author GuilhermeP
 *
 */
class ItemCardapioDAOImpl implements ItemCardapioDAO {

	/**
	 * Variavel que faz as operacoes no banco de dados
	 */
	private Session session;
	
	/**
	 * O construtor precisa receber a session do hibernate para as operacoes<br/>
	 * Seguindo a injecao de dependencias, o DAO nao � responsavel pela criacao desse objeto<br/>
	 * Vem da classe {@link DAOFactory}<br/>
	 * @param session
	 */
	public ItemCardapioDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ItemCardapio cardapio) {
		this.session.beginTransaction();
		this.session.save(cardapio);
		this.session.getTransaction().commit();
	}

	@Override
	public void remover(ItemCardapio cardapio) {
		this.session.beginTransaction();
		this.session.delete(cardapio);
		this.session.getTransaction().commit();
	}
	
	@Override
	public void removerTodos(Cardapio cardapio) {
		this.session.createQuery("delete from ItemCardapio i where i.cardapio = :crd").setParameter("crd", cardapio).executeUpdate();
	}

	@Override
	public ItemCardapio recuperar(int codigo) {
		// retorna um objeto do banco com base no tipo e a chave
		return (ItemCardapio) session.get(ItemCardapio.class, codigo);
	}

	@Override
	public List<ItemCardapio> listar(Cardapio cardapio) {
		return session.createQuery("from ItemCardapio i where i.cardapio = :crd").setParameter("crd", cardapio).list();
	}

	@Override
	public List<ItemCardapio> listar(String filtro) {
		return session.createQuery("from ItemCardapio i where i.ingredientes like :desc").setParameter("desc", "%" + filtro + "%").list();
	}
	
	@Override
	public List<ItemCardapio> listarBairro(String filtro) {
		return session.createQuery("from ItemCardapio i where i.cardapio.endereco.bairro like :desc").setParameter("desc", "%" + filtro + "%").list();
	}
	
	@Override
	public List<ItemCardapio> listarCidade(String filtro) {
		return session.createQuery("from ItemCardapio i where i.cardapio.endereco.municipio like :desc").setParameter("desc", "%" + filtro + "%").list();
	}

	@Override
	public List<ItemCardapio> listarNomePizza(String filtro) {
		return session.createQuery("from ItemCardapio i where i.descricao like :desc").setParameter("desc", "%" + filtro + "%").list();
	}

	@Override
	public List<ItemCardapio> listarNomeEstabelecimento(String filtro) {
		return session.createQuery("from ItemCardapio i where i.cardapio.endereco.nomeFantasia like :desc").setParameter("desc", "%" + filtro + "%").list();
	}

}
