package com.lea.mobile.dao.api;

import com.lea.mobile.entity.CustomerProduct;

public interface CustomerProductDao {
    void create(CustomerProduct entity);

    CustomerProduct read(int id);

    void update(CustomerProduct entity);
}
