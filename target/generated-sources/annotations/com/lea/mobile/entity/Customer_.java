package com.lea.mobile.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Date> deactivationDate;
	public static volatile SingularAttribute<Customer, Float> balance;
	public static volatile SingularAttribute<Customer, String> billingАddress;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Integer> id;
	public static volatile SingularAttribute<Customer, Date> activationDate;
	public static volatile ListAttribute<Customer, CustomerContract> contracts;
	public static volatile SingularAttribute<Customer, Boolean> activated;
	public static volatile ListAttribute<Customer, CustomerProduct> products;

}

