package com.lea.mobile.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Payment.class)
public abstract class Payment_ {

	public static volatile SingularAttribute<Payment, Float> amount;
	public static volatile SingularAttribute<Payment, Integer> id;
	public static volatile SingularAttribute<Payment, Date> paymentDate;
	public static volatile SingularAttribute<Payment, Customer> customer;

}

