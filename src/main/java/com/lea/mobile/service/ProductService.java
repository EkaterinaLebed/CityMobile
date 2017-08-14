package com.lea.mobile.service;

import com.lea.mobile.dao.api.ProductDao;
import com.lea.mobile.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public Product selectById(int id){
        return (id>0?productDao.read(id):null);
    }

    public List<Product> selectAll(){
        return productDao.selectAll();
    }

    public List<Product> search(String text){
        if(!text.isEmpty()){
            return productDao.selectLike(text);
        }
        return new ArrayList<>();
    }
}
