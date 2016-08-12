package br.com.pedidoscontabilizei.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	
    private static EntityManagerFactory emf;

    public EMF() {
        emf = Persistence.createEntityManagerFactory("PedidosContabilizeiPU");
    }
    
    public EntityManager createEntityManager() {
        if (emf == null) {            
            throw new IllegalStateException("Contexto não foi incializado.");
        }
        
        return emf.createEntityManager();
    }
    
    public void closeConnection() {
        emf.close();
    }	

}
