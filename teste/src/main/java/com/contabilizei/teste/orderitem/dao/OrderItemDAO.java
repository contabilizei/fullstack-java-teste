package com.contabilizei.teste.orderitem.dao;

import java.util.List;

import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.orderitem.model.OrderItem;
import com.contabilizei.teste.services.CRUDService;

public interface OrderItemDAO extends CRUDService<OrderItem, Integer>{
	
	
	public abstract void deleteItem(Integer orderId, Integer productId);

}
