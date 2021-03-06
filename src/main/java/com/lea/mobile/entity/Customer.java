package com.lea.mobile.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Customer")
@SuppressWarnings("unused")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String billingАddress;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "activationDate")
    private Date activationDate;

    @Column(name = "deactivationDate")
    private Date deactivationDate;

    @Column(name = "balance")
    private float balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    @Where(clause = "activated")
    List <CustomerProduct> products = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    List <CustomerContract> contracts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    List <Bill> bills = new ArrayList<>();

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

    public String getBillingАddress() {
        return billingАddress;
    }

    public void setBillingАddress(String billingАddress) {
        this.billingАddress = billingАddress;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<CustomerProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CustomerProduct> products) {
        this.products = products;
    }

    public List<CustomerContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<CustomerContract> contracts) {
        this.contracts = contracts;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", billingАddress='" + billingАddress + '\'' +
                ", activationDate=" + activationDate +
                ", deactivationDate=" + deactivationDate +
                ", balance=" + balance +
                ", services=" + products +
                ", contracts=" + contracts +
                '}';
    }
}
