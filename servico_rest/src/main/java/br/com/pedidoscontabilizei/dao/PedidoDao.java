package br.com.pedidoscontabilizei.dao;

import javax.persistence.EntityManager;

import br.com.pedidoscontabilizei.modelos.Pedido;

public class PedidoDao extends DaoGenerico<Long, Pedido> {

	public PedidoDao(EntityManager entityManager) {
		super(entityManager);
	}

}
