package com.contabilizei.teste.customer.dao.persistence;

import java.util.List;

import javax.annotation.PostConstruct;

import com.contabilizei.teste.customer.dao.CustomerDAO;
import com.contabilizei.teste.customer.model.Customer;
import com.contabilizei.teste.services.impl.CRUDServiceImpl;

public class CustomerDAOPersistence extends CRUDServiceImpl<Customer, Integer> implements CustomerDAO {
	
	public CustomerDAOPersistence(Class className) {
		super(className);
	}

	@Override
	@PostConstruct
	public void init() {
		this.entityClass = Customer.class;
	}
	
	@Override
	public Customer findById(Integer id) {
		return super.findById(id);
	}
	
	@Override
	public List<Customer> findAll() {
		return super.findAll();
	}
	
	@Override
	public void create(Customer e) {
		super.create(e);
	}
	
	@Override
	public Customer update(Customer e) {
		return super.update(e);
	}
	
	@Override
	public void delete(Integer id) {
		super.delete(id);
	}
	
	@Override
	public Customer findForDelete(Integer id) {
		return super.findForDelete(id);
	}
	
}
