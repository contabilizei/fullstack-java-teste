package com.contabilizei.teste.product.controller;

import java.util.List;

import com.contabilizei.teste.customer.model.Customer;
import com.contabilizei.teste.product.dao.ProductDAO;
import com.contabilizei.teste.product.dao.persistence.ProductDAOPersistence;
import com.contabilizei.teste.product.model.Product;

public class ProductController {
	
	ProductDAO dao = new ProductDAOPersistence(Product.class);
	
	public Product findById(Integer id){
		return dao.findById(id);
	}
	
	public List<Product> findAll(){
		return dao.findAll();
	}
	
	public void create(Product customer){
		this.dao.create(customer);
	}
	
	public Product update(Product customer){
		return dao.update(customer);
	}
	
	public void delete(Integer id){
		this.dao.delete(id);
	}

}
