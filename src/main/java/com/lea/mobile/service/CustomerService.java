package com.lea.mobile.service;

import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.entity.Customer;
import com.lea.mobile.entity.CustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public void create(Customer customer) {
        customerDao.create(customer);
    }

    public Customer selectById(int id) {
        return (id>0?customerDao.read(id):null);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public void addProduct(Customer customer, CustomerProduct customerProduct) {
        customer.getProducts().add(customerProduct);
        customerDao.update(customer);
    }

    public List<Customer> search(String text) {
        if(!text.isEmpty()) {
            return customerDao.selectLike(text);
        }
        return new ArrayList<>();
    }
}
