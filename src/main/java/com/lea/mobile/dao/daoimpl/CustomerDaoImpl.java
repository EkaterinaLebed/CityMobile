package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.entity.Customer;
import org.springframework.stereotype.Repository;

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
}
