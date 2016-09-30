package com.contabilizei.teste.orderitem.controller;

import com.contabilizei.teste.order.dao.OrderDAO;
import com.contabilizei.teste.order.dao.persistence.OrderDAOPersistence;
import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.orderitem.dao.OrderItemDAO;
import com.contabilizei.teste.orderitem.dao.persistence.OrderItemDAOPersistence;

public class OrderItemController {
	
	OrderDAO dao = new OrderDAOPersistence(Order.class);
	OrderItemDAO daoItem = new OrderItemDAOPersistence(OrderItemDAOPersistence.class);
		
	public void deleteItemOrder(Integer orderId, Integer productId){
		this. daoItem.deleteItem(orderId, productId);
	}

}
