package com.lea.mobile.dao.api;

import com.lea.mobile.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> selectLike(String text);
}
