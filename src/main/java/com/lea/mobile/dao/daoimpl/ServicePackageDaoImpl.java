package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.ServicePackageDao;
import com.lea.mobile.entity.ServicePackage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicePackageDaoImpl extends BaseDaoImpl<ServicePackage> implements ServicePackageDao {
    @Override
    public void create(ServicePackage entity) {
        create(entity);
    }

    @Override
    public ServicePackage read(int id) {
        return super.read(ServicePackage.class,id);
    }

    @Override
    public void update(ServicePackage entity) {
        super.update(entity);
    }

    @Override
    public void delete(ServicePackage entity) {
        super.delete(entity);
    }

    @Override
    public List<ServicePackage> selectAll() {
        return super.selectAll(ServicePackage.class);
    }

}
