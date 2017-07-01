package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.UserDao;
import com.lea.mobile.entity.Payment;
import com.lea.mobile.entity.Payment_;
import com.lea.mobile.entity.User;
import com.lea.mobile.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Override
    public void create(User entity) {
        super.create(entity);
    }

    @Override
    public User read(int id) {
        return super.read(User.class,id);
    }

    @Override
    public void update(User entity) {
        super.update(entity);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public List<User> selectAll() {
        return super.selectAll(User.class);
    }

    @Override
    public User selectByLogin(String login) {
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        criteria.where(builder.equal(root.get(User_.userLogin),login));

        User user = null;
        try {
            user = entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return user;
    }
}
