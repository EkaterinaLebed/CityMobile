package com.lea.mobile.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bill.class)
public abstract class Bill_ {

	public static volatile SingularAttribute<Bill, Float> amount;
	public static volatile SingularAttribute<Bill, Date> endDate;
	public static volatile SingularAttribute<Bill, Integer> id;
	public static volatile SingularAttribute<Bill, Date> startDate;
	public static volatile SingularAttribute<Bill, Customer> customer;

}

