package com.lea.mobile.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> userLogin;
	public static volatile SingularAttribute<User, byte[]> userPassword;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> userName;

}

