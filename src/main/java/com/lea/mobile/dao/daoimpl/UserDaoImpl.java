package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.UserDao;
import com.lea.mobile.entity.User;
import com.lea.mobile.entity.User_;
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
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public User read(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<User> selectAll() {
        return entityManager.createQuery("FROM User",User.class).getResultList();
    }

    @Override
    public User selectByLogin(String login) {
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
        return user;
    }
}
