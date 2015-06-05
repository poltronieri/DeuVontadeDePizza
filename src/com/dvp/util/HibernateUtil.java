package com.dvp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.dvp.model.Cardapio;
import com.dvp.model.ItemCardapio;
import com.dvp.model.Usuario;
import com.dvp.model.UsuarioEndereco;
import com.dvp.model.UsuarioPermissao;
import com.dvp.model.UsuarioPermissaoId;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			/*
			 * Configuracoes do Hibernate (arquivo hibernate.cfg.xml)
			 */
			Configuration cfg = new Configuration();
			
			// adicionando as entidade
			cfg.addAnnotatedClass(Cardapio.class);
			cfg.addAnnotatedClass(ItemCardapio.class);
			cfg.addAnnotatedClass(Usuario.class);
			cfg.addAnnotatedClass(UsuarioEndereco.class);
			cfg.addAnnotatedClass(UsuarioPermissao.class);
			cfg.addAnnotatedClass(UsuarioPermissaoId.class);
			
			// chamando o arquivo xml
			cfg.configure();
			
			// construindo a sessionFactory
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();       
			SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
			return factory;
			
			//AnnotationConfiguration cfg = new AnnotationConfiguration();
			//cfg.configure();
			//return cfg.buildSessionFactory();
		} catch (Throwable e) {
			System.err
					.println("Criação inicial do objeto SessionFactory falhou. Erro: "
							+ e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
