package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.BillDao;
import com.lea.mobile.entity.Bill;
import com.lea.mobile.entity.Bill_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings({"unused", "unchecked"})
public class BillDaoImpl implements BillDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Bill entity) {
        entityManager.persist(entity);
    }

    @Override
    public Bill read(int id) {
        return entityManager.find(Bill.class,id);
    }

    @Override
    public void update(Bill entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Bill entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Bill> selectAll() {
        return entityManager.createQuery("FROM Bill",Bill.class).getResultList();
    }

    @Override
    public List<Bill> selectByPeriod(Date startDate, Date endDate) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bill> criteria = builder.createQuery(Bill.class);
        Root<Bill> root = criteria.from(Bill.class);
        criteria.select(root);

        Predicate predicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(Bill_.startDate), startDate),
                builder.lessThanOrEqualTo(root.get(Bill_.endDate), endDate));

        criteria.where(predicate);

        return entityManager.createQuery(criteria).getResultList();
    }
}
