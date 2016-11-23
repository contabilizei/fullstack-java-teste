package com.contabilizei.teste.orderitem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.product.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="item_pedido")
@IdClass(OrderItemPK.class)
public class OrderItem{
	

	@Id
	@Column(name="id_pedido")
	private Integer orderId;
	
	@Id
	@Column(name="id_produto")
	private Integer productId;
	
	@JsonBackReference	
	@ManyToOne(fetch=FetchType.LAZY)    	
	@JoinColumn(name="id_pedido",insertable=false,updatable=false)
	private Order order;
	
	@ManyToOne
    @JoinColumn(name="id_produto",insertable=false,updatable=false)
	private Product product;
	
	@Column(name="quantidade")
	private Double quantity;
	
	public OrderItem() {
		super();
	}

	public OrderItem(Integer orderId, Integer productId, Order order, Product product, Double quantity) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}

}
