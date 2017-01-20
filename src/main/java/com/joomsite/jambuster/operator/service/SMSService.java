package com.joomsite.jambuster.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.repository.SMSRepository;

@Service
public class SMSService {
	
	@Autowired SMSRepository smsRepository;
	
	public void smsPinToCustomerOnRegistration(String senderMobileNo , String message) {
		smsRepository.smsPinToCustomerOnRegistration(senderMobileNo , message);
	}
	
	
}
