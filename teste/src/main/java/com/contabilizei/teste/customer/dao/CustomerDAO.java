package com.contabilizei.teste.customer.dao;

import java.util.List;

import com.contabilizei.teste.customer.model.Customer;
import com.contabilizei.teste.services.CRUDService;

public interface CustomerDAO extends CRUDService<Customer, Integer>{
	
	@Override
	public abstract void create(Customer e);
	
	@Override
	public abstract Customer findById(Integer id);
	
	@Override
	public abstract List<Customer> findAll();
	
	@Override
	public abstract Customer update(Customer e);
	
	@Override
	public abstract void delete(Integer id);
	
}
