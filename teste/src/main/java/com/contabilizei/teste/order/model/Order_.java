package com.contabilizei.teste.order.model;

import com.contabilizei.teste.customer.model.Customer;
import com.contabilizei.teste.orderitem.model.OrderItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-30T08:00:14.182-0300")
@StaticMetamodel(Order.class)
public class Order_ {
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, Integer> customerId;
	public static volatile SingularAttribute<Order, Date> date;
	public static volatile SingularAttribute<Order, Customer> customer;
	public static volatile SingularAttribute<Order, Double> amount;
	public static volatile SetAttribute<Order, OrderItem> orderItem;
}
