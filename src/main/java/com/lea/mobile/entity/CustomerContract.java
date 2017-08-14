package com.lea.mobile.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CustomerContract")
@SuppressWarnings("unused")
public class CustomerContract {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    @Column(name = "description")
    private String description;
}
