package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.CustomerProductDao;
import com.lea.mobile.entity.CustomerProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
@SuppressWarnings({"unused", "unchecked"})
public class CustomerProductDaoImpl implements CustomerProductDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(CustomerProduct entity) {
        entityManager.persist(entity);
    }

    @Override
    public CustomerProduct read(int id){
        return entityManager.find(CustomerProduct.class, id);
    }

    @Override
    public void update(CustomerProduct entity) {
        entityManager.merge(entity);
    }
}
