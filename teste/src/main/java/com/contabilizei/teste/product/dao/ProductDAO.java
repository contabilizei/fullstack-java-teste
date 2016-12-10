package com.contabilizei.teste.product.dao;

import java.util.List;

import com.contabilizei.teste.product.model.Product;
import com.contabilizei.teste.services.CRUDService;

public interface ProductDAO extends CRUDService<Product, Integer>{
	
	@Override
	public abstract void create(Product e);
	
	@Override
	public abstract Product findById(Integer id);
	
	@Override
	public abstract List<Product> findAll();
	
	@Override
	public abstract Product update(Product e);
	
	@Override
	public abstract void delete(Integer id);
	
}
