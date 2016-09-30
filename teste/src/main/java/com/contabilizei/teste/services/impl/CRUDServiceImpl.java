package com.contabilizei.teste.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.contabilizei.teste.services.CRUDService;

public abstract class CRUDServiceImpl<E, ID> implements CRUDService<E, ID>{
	
	protected EntityManagerFactory emf; 
	protected EntityManager em; 
	protected Class<E> entityClass;
	protected EntityTransaction tx;
	
	public CRUDServiceImpl(Class className){
		this.emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		this.em  = emf.createEntityManager();
		this.tx = em.getTransaction();
		this.entityClass = className;
	}
	
	public abstract void init();
	
	@Override
	public void create(E e) {
		try{
			tx.begin();
			em.persist(e);
			tx.commit();
		}finally{
			em.close();
		}
	}
	
	@Override
	public E update(E e) {
		try{
			tx.begin();
			em.merge(e);
			tx.commit();
		}finally{
			em.close();
		}
		return e;
	}
	
	@Override
	public void delete(ID id) {
		try{
			tx.begin();
			E e = findForDelete(id);
			em.remove(e);
			tx.commit();
		}finally{
			em.close();
		}
	}
	
	@Override
	public E findById(ID id) {
		try{
			tx.begin();
			return em.find(entityClass, id);
		}finally{
			em.close();
		}
	}
	
	@Override
	public List<E> findAll() {
		try{
			tx.begin();
			return em.createQuery("SELECT e FROM "+entityClass.getSimpleName()+" e").getResultList();
		}finally{
			em.close();
		}
	}
	
	@Override
	public E findForDelete(ID id) {
		return em.find(entityClass, id);
	}
	
}
