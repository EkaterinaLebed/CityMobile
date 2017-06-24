package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.BillDao;
import com.lea.mobile.entity.Bill;
import com.lea.mobile.entity.Bill_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class BillDaoImpl extends BaseDaoImpl<Bill> implements BillDao{
    @Override
    public void create(Bill entity) {
        super.create(entity);
    }

    @Override
    public Bill read(int id) {
        return super.read(Bill.class,id);
    }

    @Override
    public void update(Bill entity) {
        super.update(entity);
    }

    @Override
    public void delete(Bill entity) {
        super.delete(entity);
    }

    @Override
    public List<Bill> selectAll() {
        return super.selectAll(Bill.class);
    }

    @Override
    public List<Bill> selectByPeriod(Date startDate, Date endDate) {
        EntityManager entityManager = AppSessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bill> criteria = builder.createQuery(Bill.class);
        Root<Bill> root = criteria.from(Bill.class);
        criteria.select(root);

        Predicate predicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(Bill_.startDate), startDate),
                builder.lessThanOrEqualTo(root.get(Bill_.endDate), endDate));

        criteria.where(predicate);

        List<Bill> list= entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }
}
