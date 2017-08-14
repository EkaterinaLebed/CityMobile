package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.entity.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings({"unused", "unchecked"})
public class CustomerDaoImpl implements CustomerDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Customer entity) {
        entityManager.persist(entity);
    }

    @Override
    public Customer read(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void update(Customer entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Customer entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Customer> selectAll() {
        return entityManager.createQuery("FROM Customer",Customer.class).getResultList();
    }

    @Override
    public List<Customer> selectLike(String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);
        criteria.where(builder.like(root.get("name"),"%"+text+"%"));

        return entityManager.createQuery(criteria).getResultList();
    }
}
