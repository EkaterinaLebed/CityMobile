package com.lea.mobile.app;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class AppSessionFactory {
    static private SessionFactory sessionFactory;

//    <bean id="sessionFactory"
//    class="org.springframework.orm.hibernate.LocalSessionFactoryBean">
//    <property name="dataSource" ref="dataSource"/>
//    <property name="configLocation" value="classpath:hibernate.cfg.xml" />
//    </bean>

    static {
        sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    public static EntityManager createEntityManager(){
        return sessionFactory.createEntityManager();
    }

    public static SessionFactory getInstance(){
        return sessionFactory;
    }

    public static void close(){
        sessionFactory.close();
    }

}