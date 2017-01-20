package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.ChangePassword;
import com.joomsite.jambuster.operator.bean.Login;
import com.joomsite.jambuster.operator.bean.Operator;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired OperatorRepository operatorRepository;
	@Autowired SMSService smsService;
	
	public Response<Object> login(Login login) {
		Response<Object> retVal= null;
		Operator customer = operatorRepository.login(login);
		
		if(customer != null) {
			retVal = new Response<Object>("SUCCESS", "SUCCESSFUL LOGIN", customer);
		}
		 else 
			 retVal = new Response<Object>("FAILURE", "INVALID USERNAME AND PASSWORD");
		return retVal;
	}

	public void editOperator(long operatorId, Operator operator) {
		operatorRepository.editCustomer(operatorId , operator);
	}
	
	public Operator getOperatorByMobile(String mobile) {
		return operatorRepository.getOperatorByMobile(mobile);
	}
	
	public Operator getOperatorByOperatorId(long operatorId) {
		return operatorRepository.getOperatorByOperatorId(operatorId);
	}
	
	public List<Operator> getOperatorByStatus(String status) {
		return operatorRepository.getOperatorByStatus(status);
	}
	
	public String getUserPasswordByOperatorId(long operatorId) {
		return operatorRepository.getUserPasswordByOperatorId(operatorId);
	}
	
	public String getUserPasswordByMobile(String mobile) {
		return operatorRepository.getUserPasswordByMobile(mobile);
	}

	public void forgetPin(String mobile) {
		// TODO Auto-generated method stub
		String pin = getUserPasswordByMobile(mobile);
		if(StringUtils.isNotBlank(pin)) {
			smsService.smsPinToCustomerOnRegistration(mobile , "Your pin for login is " + pin);			
		} else {
			throw new RuntimeException("Invalid Mobile Number");
		}
	}

	public void changePassword(long operatorId, ChangePassword changePassword) {
		// TODO Auto-generated method stub
		String userPassword = getUserPasswordByOperatorId(operatorId);
		if(userPassword != null) {
			if(userPassword.equals(changePassword.oldPin)) {
				operatorRepository.changePassword(operatorId , changePassword);
				Operator operator = getOperatorByOperatorId(operatorId);
			} else {
				throw new RuntimeException("");
			}
		}
	}

	public void changePasswordByMobile(String mobile , ChangePassword changePassword) {
		String userPassword = getUserPasswordByMobile(mobile);
		System.out.println("userPassword " + userPassword);
		if(userPassword != null) {
			System.out.println("userPassword " + userPassword + " oldPin " + changePassword.oldPin);
			if(userPassword.equals(changePassword.oldPin)) {
				operatorRepository.changePasswordByMobile(mobile , changePassword);
				Operator operator = getOperatorByMobile(mobile);
			} else {
				throw new RuntimeException("Wrong Old Pin");
			}
		}
	}

}
