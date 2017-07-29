package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
    @Override
    public void create(Customer entity) {
        super.create(entity);
    }

    @Override
    public Customer read(int id) {
        return super.read(Customer.class,id);
    }

    @Override
    public void update(Customer entity) {
        super.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        super.delete(entity);
    }

    @Override
    public List<Customer> selectAll() {
        return super.selectAll(Customer.class);
    }

    @Override
    public List<Customer> selectLike(String text) {
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);
        criteria.where(builder.like(root.get("name"),"%"+text+"%"));

        List<Customer> list = entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }
}
