package com.nagarro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nagarro.service.APIService;
import com.nagarro.service.APIServiceImpl;
import com.nagarro.service.CustomerService;
import com.nagarro.service.CustomerServiceImpl;
import com.nagarro.util.Constants;

@Configuration
public class AppConfigService {

	@Bean
	public CustomerService getCustomerService() {
		return new CustomerServiceImpl();
	}
	
	@Bean
	public APIService getAPIService() {
		return new APIServiceImpl();
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(Constants.IMAGE_NAX_SIZE);
		return multipartResolver;
	}
}
