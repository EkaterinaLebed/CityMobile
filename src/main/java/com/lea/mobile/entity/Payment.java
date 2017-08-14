package com.lea.mobile.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payment")
@SuppressWarnings("unused")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "amount")
    private float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customer=" + customer +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}
