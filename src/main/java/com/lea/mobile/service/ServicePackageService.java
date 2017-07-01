package com.lea.mobile.service;

import com.lea.mobile.dao.api.ServicePackageDao;
import com.lea.mobile.entity.ServicePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePackageService {
    @Autowired
    ServicePackageDao servicePackageDao;

    public List<ServicePackage> selectAll(){
        return servicePackageDao.selectAll();
    }
}
