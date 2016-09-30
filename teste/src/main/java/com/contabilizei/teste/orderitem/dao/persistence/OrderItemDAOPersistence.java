package com.contabilizei.teste.orderitem.dao.persistence;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.contabilizei.teste.orderitem.dao.OrderItemDAO;
import com.contabilizei.teste.orderitem.model.OrderItem;
import com.contabilizei.teste.orderitem.model.OrderItemPK;
import com.contabilizei.teste.orderitem.model.OrderItem_;
import com.contabilizei.teste.services.impl.CRUDServiceImpl;

public class OrderItemDAOPersistence extends CRUDServiceImpl<OrderItem, Integer> implements OrderItemDAO{

	public OrderItemDAOPersistence(Class className) {
		super(className);
	}

	@Override
	@PostConstruct
	public void init() {
		this.entityClass = OrderItem.class;
	}
	
	@Override
	public void deleteItem(Integer orderId, Integer productId) {
		
		CriteriaBuilder cb = super.em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = cb.createQuery(OrderItem.class);
		Root<OrderItem> root = cq.from(OrderItem.class);
		Predicate predicate = cb.equal(root.get(OrderItem_.orderId), orderId);
		predicate = cb.and(predicate,cb.equal(root.get(OrderItem_.product), productId));
		cq.where(predicate);
		TypedQuery<OrderItem> query = em.createQuery(cq);
		
		try{
			super.tx.begin();
			super.em.find(OrderItem.class, new OrderItemPK(orderId, productId));
			super.em.remove(query.getSingleResult());
			super.tx.commit();
		}finally{
			super.em.close();
		}
	}

}
