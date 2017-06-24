package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.PaymentDao;
import com.lea.mobile.entity.Bill;
import com.lea.mobile.entity.Bill_;
import com.lea.mobile.entity.Payment;
import com.lea.mobile.entity.Payment_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements PaymentDao{
    @Override
    public void create(Payment entity) {
        super.create(entity);
    }

    @Override
    public Payment read(int id) {
        return super.read(Payment.class,id);
    }

    @Override
    public void update(Payment entity) {
        super.update(entity);
    }

    @Override
    public void delete(Payment entity) {
        super.delete(entity);
    }

    @Override
    public List<Payment> selectAll() {
        return super.selectAll(Payment.class);
    }

    @Override
    public List<Payment> selectByPeriod(Date startDate, Date endDate) {
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        Root<Payment> root = criteria.from(Payment.class);
        criteria.select(root);

        Predicate predicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(Payment_.paymentDate), startDate),
                builder.lessThanOrEqualTo(root.get(Payment_.paymentDate), endDate));

        criteria.where(predicate);

        List<Payment> list= entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }
}
