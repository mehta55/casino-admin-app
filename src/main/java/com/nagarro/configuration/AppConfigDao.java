package com.nagarro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.dao.CustomerDao;
import com.nagarro.dao.CustomerDaoImpl;

@Configuration
public class AppConfigDao {
	
	@Bean
	public CustomerDao getCustomerDaoImpl() {
		return new CustomerDaoImpl();
	}
	
}
