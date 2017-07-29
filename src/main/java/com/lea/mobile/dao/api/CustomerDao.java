package com.lea.mobile.dao.api;


import com.lea.mobile.entity.Customer;

import java.util.List;

public interface CustomerDao extends GenericDao<Customer> {
    public List<Customer> selectLike(String text);
}
