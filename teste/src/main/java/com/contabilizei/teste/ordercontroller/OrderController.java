package com.contabilizei.teste.ordercontroller;

import java.util.List;

import com.contabilizei.teste.order.dao.OrderDAO;
import com.contabilizei.teste.order.dao.persistence.OrderDAOPersistence;
import com.contabilizei.teste.order.model.Order;

public class OrderController {
	
	OrderDAO dao = new OrderDAOPersistence(Order.class);
		
	public Order findById(Integer id){
		return dao.findById(id);
	}
	
	public List<Order> findAll(){
		return dao.findAll();
	}
	
	public void create(Order order){
		this.dao.create(order);
	}
	
	public Order update(Order order){
		return dao.update(order);
	}
	
	public void delete(Integer id){
		this.dao.delete(id);
	}

}
