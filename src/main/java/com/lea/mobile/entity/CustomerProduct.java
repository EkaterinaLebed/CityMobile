package com.lea.mobile.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CustomerService")
@SuppressWarnings("unused")
public class CustomerProduct {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @Column(name = "dateActivated")
    private Date dateActivated;

    @Column(name = "dateDeactivated")
    private Date dateDeactivated;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateActivated() {
        return dateActivated;
    }

    public void setDateActivated(Date dateActivated) {
        this.dateActivated = dateActivated;
    }

    public Date getDateDeactivated() {
        return dateDeactivated;
    }

    public void setDateDeactivated(Date dateDeactivated) {
        this.dateDeactivated = dateDeactivated;
    }

    public String getName(){
        return (product!=null? product.getName(): null);
    }

    public float getPayment(){
        return (product!=null? product.getPayment(): 0);
    }


}
