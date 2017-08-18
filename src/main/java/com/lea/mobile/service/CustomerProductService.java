package com.lea.mobile.service;

import com.lea.mobile.dao.api.CustomerProductDao;
import com.lea.mobile.entity.CustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerProductService {
    @Autowired
    CustomerProductDao customerProductDao;

    public CustomerProduct selectById(int id){
        return customerProductDao.read(id);
    }

    public void update(CustomerProduct customerProduct) {
        customerProductDao.update(customerProduct);
    }

    public void create(CustomerProduct customerProduct) {
        customerProductDao.create(customerProduct);
    }
}
