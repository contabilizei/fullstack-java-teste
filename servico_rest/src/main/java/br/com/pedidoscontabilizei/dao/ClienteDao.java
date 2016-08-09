package br.com.pedidoscontabilizei.dao;

import javax.persistence.EntityManager;

import br.com.pedidoscontabilizei.modelos.Cliente;

public class ClienteDao extends DaoGenerico<String, Cliente> {
	
	public ClienteDao(EntityManager entityManager) {
		super(entityManager);
	}
	
}
