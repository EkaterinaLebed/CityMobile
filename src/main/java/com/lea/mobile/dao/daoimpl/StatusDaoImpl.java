package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.StatusDao;
import com.lea.mobile.entity.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDaoImpl extends BaseDaoImpl<Status> implements StatusDao{
    @Override
    public void create(Status entity) {
        create(entity);
    }

    @Override
    public Status read(int id) {
        return read(Status.class,id);
    }

    @Override
    public void update(Status entity) {
        super.update(entity);
    }

    @Override
    public void delete(Status entity) {
        super.delete(entity);
    }

    @Override
    public List<Status> selectAll() {
        return super.selectAll(Status.class);
    }
}
