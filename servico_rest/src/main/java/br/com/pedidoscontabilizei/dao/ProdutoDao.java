package br.com.pedidoscontabilizei.dao;

import javax.persistence.EntityManager;

import br.com.pedidoscontabilizei.modelos.Produto;

public class ProdutoDao extends DaoGenerico<Long, Produto> {
    
    public ProdutoDao(EntityManager entityManager) {
        super(entityManager);
    }    
}
