package com.contabilizei.teste.services;

import java.util.List;

public interface CRUDService <E, ID>{

	void create(E e);
	
	E update(E e);
	
	void delete(ID id);
	
	E findById(ID id);
	
	List<E> findAll();
	
	E findForDelete(ID id);

}
