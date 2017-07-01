package com.lea.mobile.service;

import com.lea.mobile.dao.api.UserDao;
import com.lea.mobile.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User selectByLogin(String login){
        return userDao.selectByLogin(login);
    }

    public void create(User user) {
        userDao.create(user);
    }
}
