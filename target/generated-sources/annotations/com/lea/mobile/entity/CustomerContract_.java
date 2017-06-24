package com.lea.mobile.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerContract.class)
public abstract class CustomerContract_ {

	public static volatile SingularAttribute<CustomerContract, Date> date;
	public static volatile SingularAttribute<CustomerContract, String> name;
	public static volatile SingularAttribute<CustomerContract, String> description;
	public static volatile SingularAttribute<CustomerContract, Integer> id;
	public static volatile SingularAttribute<CustomerContract, Customer> customer;

}

