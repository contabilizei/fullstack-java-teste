package com.contabilizei.teste.customer.controller;

import java.util.List;

import com.contabilizei.teste.customer.dao.CustomerDAO;
import com.contabilizei.teste.customer.dao.persistence.CustomerDAOPersistence;
import com.contabilizei.teste.customer.model.Customer;

public class CustomerController {
	
	CustomerDAO dao = new CustomerDAOPersistence(Customer.class);
	
	public Customer findById(Integer id){
		return dao.findById(id);
	}
	
	public List<Customer> findAll(){
		return dao.findAll();
	}
	
	public void create(Customer customer){
		this.dao.create(customer);
	}
	
	public Customer update(Customer customer){
		return dao.update(customer);
	}
	
	public void delete(Integer id){
		this.dao.delete(id);
	}

}
