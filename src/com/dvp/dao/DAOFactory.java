package com.dvp.dao;

import com.dvp.util.HibernateUtil;

/**
 * CLasse que cria os DAOs utilizados no sistema
 * @author GuilhermeP
 *
 */
public final class DAOFactory {

	/**
	 * Retorna um novo objeto dao para operacoes com a entidade aluno
	 * @return
	 */
	public static final UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOImpl(HibernateUtil.getSessionFactory().openSession());
	}
	
	public static final UsuarioEnderecoDAO getUsuarioEnderecoDAO(){
		return new UsuarioEnderecoDAOImpl(HibernateUtil.getSessionFactory().openSession());
	}
	
	public static final CardapioDAO getCardapioDAO(){
		return new CardapioDAOImpl(HibernateUtil.getSessionFactory().openSession());
	}
	
	public static final ItemCardapioDAO getItemCardapioDAO(){
		return new ItemCardapioDAOImpl(HibernateUtil.getSessionFactory().openSession());
	}
	
	public static final UsuarioPermissaoDAO getUsuarioPermissaoDAO(){
		return new UsuarioPermissaoDAOImpl(HibernateUtil.getSessionFactory().openSession());
	}
	
}
