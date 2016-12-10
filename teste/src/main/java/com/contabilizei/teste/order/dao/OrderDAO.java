package com.contabilizei.teste.order.dao;

import java.util.List;

import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.services.CRUDService;

public interface OrderDAO extends CRUDService<Order, Integer>{
	
	@Override
	public abstract void create(Order e);
	
	@Override
	public abstract Order findById(Integer id);
	
	@Override
	public abstract List<Order> findAll();
	
	@Override
	public abstract Order update(Order e);
	
	@Override
	public abstract void delete(Integer id);
	
}
