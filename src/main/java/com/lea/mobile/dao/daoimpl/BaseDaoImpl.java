package com.lea.mobile.dao.daoimpl;


import com.lea.mobile.app.AppSessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public abstract class BaseDaoImpl<T>{

    public void create(T entity){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T read(java.lang.Class<T> aClass, int id){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        T entity = entityManager.find(aClass,id);

        entityManager.getTransaction().commit();
        entityManager.close();

        return  entity;
    }

    public void update(T entity){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(T entity){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<T> selectAll(java.lang.Class<T> aClass){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from " + aClass.getSimpleName());
        List<T> list = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }
}
