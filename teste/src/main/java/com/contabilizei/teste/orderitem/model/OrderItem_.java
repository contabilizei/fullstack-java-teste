package com.contabilizei.teste.orderitem.model;

import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.product.model.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-30T08:00:14.199-0300")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ {
	public static volatile SingularAttribute<OrderItem, Integer> orderId;
	public static volatile SingularAttribute<OrderItem, Integer> productId;
	public static volatile SingularAttribute<OrderItem, Order> order;
	public static volatile SingularAttribute<OrderItem, Product> product;
	public static volatile SingularAttribute<OrderItem, Double> quantity;
}
