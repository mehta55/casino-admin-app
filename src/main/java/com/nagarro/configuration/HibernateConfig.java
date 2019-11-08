package com.nagarro.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.model.Customer;

@Configuration
public class HibernateConfig {

	@Bean 
	public SessionFactory getSessionFactory() {
		
		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
		configuration.addAnnotatedClass(Customer.class);
		
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	    configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/casino_admin");
	    configuration.setProperty("hibernate.connection.username", "root");
	    configuration.setProperty("hibernate.connection.password", "root");
	    configuration.setProperty("hibernate.show_sql", "true");
	    configuration.setProperty("hibernate.hbm2ddl.auto", "update");

	    return configuration.buildSessionFactory();
		
	}
	
}
