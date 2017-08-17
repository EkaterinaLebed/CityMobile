package com.lea.mobile.service;

import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.entity.Customer;
import com.lea.mobile.entity.CustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public void create(Customer customer) {
        customerDao.create(customer);
    }

    public void activated(Customer customer,boolean activated){
        if(activated){
            customer.setActivationDate(new Date());
            customer.setDeactivationDate(null);
            customer.setActivated(true);
            customerDao.update(customer);
        }else {
            customer.setDeactivationDate(new Date());
            customer.setActivated(false);
            customerDao.update(customer);
        }
    }

    public Customer selectById(int id) {
        return (id>0?customerDao.read(id):null);
    }

    public void addProduct(Customer customer, CustomerProduct customerProduct) {
        customer.getProducts().add(customerProduct);
        customerDao.update(customer);
    }

    public List<Customer> search(String text) {
        if(!text.isEmpty()) {
            return customerDao.selectLike(text);
        }
        return null;
    }
}
