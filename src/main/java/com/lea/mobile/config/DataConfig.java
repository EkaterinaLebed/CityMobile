package com.lea.mobile.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:app.properties")
public class DataConfig {
    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty("db.entitymanager.packages.to.scan"));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",env.getRequiredProperty("db.hibernate.dialect"));
        properties.put("hibernate.show_sql",env.getRequiredProperty("db.hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto",env.getRequiredProperty("db.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.connection.CharSet",env.getRequiredProperty("db.hibernate.connection.CharSet"));
        properties.put("hibernate.connection.characterEncoding",env.getRequiredProperty("db.hibernate.connection.characterEncoding"));
        properties.put("hibernate.connection.useUnicode",env.getRequiredProperty("db.hibernate.connection.useUnicode"));
        return properties;
    }
}
