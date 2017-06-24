package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.ServiceDao;
import com.lea.mobile.entity.Service;

import java.util.List;

public class ServiceDaoImpl extends BaseDaoImpl<Service> implements ServiceDao{
    @Override
    public void create(Service entity) {
        create(entity);
    }

    @Override
    public Service read(int id) {
        return super.read(Service.class,id);
    }

    @Override
    public void update(Service entity) {
        super.update(entity);
    }

    @Override
    public void delete(Service entity) {
        super.delete(entity);
    }

    @Override
    public List<Service> selectAll() {
        return super.selectAll(Service.class);
    }
}
