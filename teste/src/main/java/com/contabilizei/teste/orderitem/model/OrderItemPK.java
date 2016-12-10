package com.contabilizei.teste.orderitem.model;

import java.io.Serializable;

public class OrderItemPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer productId;
	
	
	public OrderItemPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItemPK(Integer orderId, Integer productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderItemPK [orderId=" + orderId + ", productId=" + productId + "]";
	}
	
}
