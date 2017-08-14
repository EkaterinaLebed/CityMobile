package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.StatusDao;
import com.lea.mobile.entity.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings({"unused", "unchecked"})
public class StatusDaoImpl implements StatusDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Status entity) {
        entityManager.persist(entity);
    }

    @Override
    public Status read(int id) {
        return entityManager.find(Status.class, id);
    }

    @Override
    public void update(Status entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Status entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Status> selectAll() {
        return entityManager.createQuery("FROM Status",Status.class).getResultList();
    }
}
