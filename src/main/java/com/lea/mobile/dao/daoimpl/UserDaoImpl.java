package com.lea.mobile.dao.daoimpl;

import com.lea.mobile.dao.api.UserDao;
import com.lea.mobile.entity.User;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Override
    public void create(User entity) {
        super.create(entity);
    }

    @Override
    public User read(int id) {
        return super.read(User.class,id);
    }

    @Override
    public void update(User entity) {
        super.update(entity);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public List<User> selectAll() {
        return super.selectAll(User.class);
    }
}
