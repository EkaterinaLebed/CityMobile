package com.lea.mobile.dao.api;

import com.lea.mobile.entity.User;

public interface UserDao extends GenericDao<User> {
    User selectByLogin(String login);
}
