package com.lea.mobile.service;

import com.lea.mobile.dao.api.UserDao;
import com.lea.mobile.entity.User;
import com.lea.mobile.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void makePassword(User user, String pwd){
        user.setPassword(new BCryptPasswordEncoder().encode(pwd));
    }

    public void makeRole(User user, String role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole("ROLE_"+role);
        user.getRoles().add(userRole);
    }
}
