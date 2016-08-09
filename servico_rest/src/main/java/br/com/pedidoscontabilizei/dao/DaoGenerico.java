package br.com.pedidoscontabilizei.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

public class DaoGenerico<PK, T> {

    protected EntityManager entityManager = null;

    public DaoGenerico(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T buscaPeloId(PK id) {
        return (T) entityManager.find(getTypeClass(), id);
    }

    public List<T> buscaTodos() {
        return entityManager.createQuery(("SELECT e FROM " + getTypeClass().getSimpleName() + " e")).getResultList();
    }

    public void inserir(T entidade) {
        entityManager.getTransaction().begin();
        entityManager.persist(entidade);
        entityManager.getTransaction().commit();
    }

    public void alterar(T entidade) {
        entityManager.getTransaction().begin();
        entityManager.merge(entidade);
        entityManager.getTransaction().commit();
    }

    public void deletar(T entidade) {
        entityManager.getTransaction().begin();
        entityManager.remove(entidade);
        entityManager.getTransaction().commit();
    }

    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}

