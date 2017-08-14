package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.PaymentDao;
import com.lea.mobile.entity.Payment;
import com.lea.mobile.entity.Payment_;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings({"unused", "unchecked"})
public class PaymentDaoImpl implements PaymentDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Payment entity) {
        entityManager.persist(entity);
    }

    @Override
    public Payment read(int id) {
        return entityManager.find(Payment.class, id);
    }

    @Override
    public void update(Payment entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Payment entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Payment> selectAll() {
        return entityManager.createQuery("FROM Payment",Payment.class).getResultList();
    }

    @Override
    public List<Payment> selectByPeriod(Date startDate, Date endDate) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        Root<Payment> root = criteria.from(Payment.class);
        criteria.select(root);

        Predicate predicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(Payment_.paymentDate), startDate),
                builder.lessThanOrEqualTo(root.get(Payment_.paymentDate), endDate));

        criteria.where(predicate);

        return entityManager.createQuery(criteria).getResultList();
    }
}
