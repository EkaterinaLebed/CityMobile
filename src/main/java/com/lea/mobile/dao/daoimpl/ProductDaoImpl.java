package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.ProductDao;
import com.lea.mobile.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    @Override
    public void create(Product entity) {
        super.create(entity);
    }

    @Override
    public Product read(int id) {
        return super.read(Product.class,id);
    }

    @Override
    public void update(Product entity) {
        super.update(entity);
    }

    @Override
    public void delete(Product entity) {
        super.delete(entity);
    }

    @Override
    public List<Product> selectAll() {
        return super.selectAll(Product.class);
    }

    @Override
    public List<Product> selectLike(String text){
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.where(builder.like(root.get("name"),"%"+text+"%"));

        List<Product> list = entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }

}
