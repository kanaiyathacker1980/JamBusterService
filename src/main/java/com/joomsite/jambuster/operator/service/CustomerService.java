package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joomsite.jambuster.operator.bean.Customer;
import com.joomsite.jambuster.operator.bean.Login;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired CustomerRepository customerRepository;
	@Autowired VehicleService vehicleService;

	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.getCustomerByEmail(email);
		if(customer != null)
			customer.vehicles = vehicleService.getVehiclesByCustomerId(customer.customerId);
		return customer;
	}
	
	public Customer getCustomerByMobile(String mobile) {
		Customer customer = customerRepository.getCustomerByMobile(mobile);
		if(customer != null)
			customer.vehicles = vehicleService.getVehiclesByCustomerId(customer.customerId);
		return customer;
	}

	public void editCustomer(long customerId, Customer customer) {
		customerRepository.editCustomer(customerId , customer);
	}

	public Customer getCustomerByCustomerId(long customerId) {
		Customer customer = customerRepository.getCustomerByCustomerId(customerId);
		if(customer != null)
			customer.vehicles = vehicleService.getVehiclesByCustomerId(customerId);
		return customer;
	}
	
	public List<Customer> getCustomerByStatus(String status) {
		return customerRepository.getCustomerByStatus(status);
	}

	public Response<Object> login(Login login) {
		Response<Object> retVal= null;
		Customer customer = customerRepository.login(login);
		
		if(customer != null) {
			customer.vehicles = vehicleService.getVehiclesByCustomerId(customer.customerId);
			retVal = new Response<Object>("SUCCESS", "SUCCESSFUL LOGIN", customer);
		}
		 else 
			 retVal = new Response<Object>("FAILURE", "INVALID USERNAME AND PASSWORD");
		return retVal;
	}


}
