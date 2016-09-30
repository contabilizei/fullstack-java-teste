package com.contabilizei.teste.order.dao.persistence;

import java.util.List;

import javax.annotation.PostConstruct;

import com.contabilizei.teste.order.dao.OrderDAO;
import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.services.impl.CRUDServiceImpl;

public class OrderDAOPersistence extends CRUDServiceImpl<Order, Integer> implements OrderDAO {
	
	public OrderDAOPersistence(Class className) {
		super(className);
	}

	@Override
	@PostConstruct
	public void init() {
		this.entityClass = Order.class;
	}
	
	@Override
	public Order findById(Integer id) {
		return super.findById(id);
	}
	
	@Override
	public List<Order> findAll() {
		return super.findAll();
	}
	
	@Override
	public void create(Order e) {
		super.create(e);
	}
	
	@Override
	public Order update(Order e) {
		return super.update(e);
	}
	
	@Override
	public void delete(Integer id) {
		super.delete(id);
	}	
	
}
