package com.lea.mobile.entity;

import javax.persistence.*;

@Entity
@Table(name = "ServiceStatus")
@SuppressWarnings("unused")
public class Status {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
