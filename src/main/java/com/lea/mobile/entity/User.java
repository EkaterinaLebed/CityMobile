package com.lea.mobile.entity;

import javax.persistence.*;

@Entity
@Table(name = "User")
@SuppressWarnings("unused")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "customerId", nullable = false)
    private int customerId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userLogin")
    private String userLogin;

    @Column(name = "userPassword")
    private byte[] userPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public byte[] getPassword() {
        return userPassword;
    }

    public void setPassword(byte[] userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
