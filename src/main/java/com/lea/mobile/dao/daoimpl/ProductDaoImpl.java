package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.ProductDao;
import com.lea.mobile.entity.Product;
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
public class ProductDaoImpl implements ProductDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Product entity) {
        entityManager.persist(entity);
    }

    @Override
    public Product read(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void update(Product entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Product entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Product> selectAll() {
        return entityManager.createQuery("from Product",Product.class).getResultList();
    }

    @Override
    public List<Product> selectLike(String text){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.where(builder.like(root.get("name"),"%"+text+"%"));

        return entityManager.createQuery(criteria).getResultList();
    }

}
