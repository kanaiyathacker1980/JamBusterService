package com.joomsite.jambuster.operator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

	@Value("${jdbc.driver}") String jdbcDriver;
	@Value("${jdbc.url}") String jdbcUrl;
	@Value("${jdbc.username}") String jdbcUsername;
	@Value("${jdbc.password}") String jdbcPassword;
	
	public static void main(String[] args) {	
//		 SpringApplication.run(ApplicationConfig.class, args);
//        ConfigurableApplicationContext run = SpringApplication.run(ApplicationConfig.class, args);
//        CustomerController bean = run.getBean(CustomerController.class);
//        Customer customer = new Customer();
//        customer.firstName  = "Kanaiya";
//        customer.middleName = "midle";
//        customer.lastName = "last";
//        customer.addressLine1 = "add line 1";
//        customer.addressLine2 = "add line 2";	
//        customer.city = "city";
//        customer.state = "state";
//        customer.country = "country";
//        customer.dateOfBirth = "12-12-1990";
//        customer.drivingLicenceNumber = "LIC-NO";
//        customer.drivingLicenceStartDate = "12-12-1990";
//        customer.drivingLicenceEndDate = "12-12-1990";
//        customer.email ="kana@gmail.com";
//        customer.mobile = "09898989888";
//        
//		bean.createCustomer(customer );
        //run.stop();
	}
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() {
		System.out.println("Kanaiya-dev");
		DriverManagerDataSource bean = new DriverManagerDataSource();
		bean.setDriverClassName(jdbcDriver);
		bean.setUrl(jdbcUrl);
		bean.setUsername(jdbcUsername);
		bean.setPassword(jdbcPassword);
		return bean;
	}
	
	@Bean
	public FreeMarkerConfigurationFactoryBean freemarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/ftl/");
		return freeMarkerConfigurationFactoryBean;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/*@Bean
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver bean = new CommonsMultipartResolver();
		bean.setMaxUploadSize(9000000);
		return bean;
	}*/
	
}
