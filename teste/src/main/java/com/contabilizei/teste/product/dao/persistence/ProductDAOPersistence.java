package com.contabilizei.teste.product.dao.persistence;

import java.util.List;

import javax.annotation.PostConstruct;

import com.contabilizei.teste.customer.model.Customer;
import com.contabilizei.teste.product.dao.ProductDAO;
import com.contabilizei.teste.product.model.Product;
import com.contabilizei.teste.services.impl.CRUDServiceImpl;

public class ProductDAOPersistence extends CRUDServiceImpl<Product, Integer> implements ProductDAO {
	
	public ProductDAOPersistence(Class className) {
		super(className);
	}

	@Override
	@PostConstruct
	public void init() {
		this.entityClass = Product.class;
	}
	
	@Override
	public Product findById(Integer id) {
		return super.findById(id);
	}
	
	@Override
	public List<Product> findAll() {
		return super.findAll();
	}
	
	@Override
	public void create(Product e) {
		super.create(e);
	}
	
	@Override
	public Product update(Product e) {
		return super.update(e);
	}
	
	@Override
	public void delete(Integer id) {
		super.delete(id);
	}
	
	@Override
	public Product findForDelete(Integer id) {
		return super.findForDelete(id);
	}
	
}
