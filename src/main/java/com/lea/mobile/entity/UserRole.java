package com.lea.mobile.entity;

import javax.persistence.*;

@Entity
@Table(name = "sps_user_roles")
@SuppressWarnings("unused")
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user ;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
