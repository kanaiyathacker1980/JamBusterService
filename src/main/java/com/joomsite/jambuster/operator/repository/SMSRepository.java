package com.joomsite.jambuster.operator.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SMSRepository {

	@Autowired RestTemplate restTemplate;
	@Value("${nimbusit.sms.userName}")   String userName;
	@Value("${nimbusit.sms.password}") String password;
	@Value("${nimbusit.sms.senderId}") String sender;
	@Value("${nimbusit.api.url}") String apiUrl;
	
	public void smsPinToCustomerOnRegistration(String senderMobileNo, String message) {
		Map<String , String> urlVariables = new HashMap<String , String>();
		urlVariables.put("username", userName);
		urlVariables.put("password", password);
		urlVariables.put("sender", sender);
		urlVariables.put("sendto", senderMobileNo);
		urlVariables.put("message", message);
		StringBuilder stringBuilder = new StringBuilder(apiUrl);
		stringBuilder.append("username=").append(userName).append("&").append("password=").append(password).append("&")
					 .append("sender=").append(sender).append("&").append("sendto=").append(senderMobileNo).append("&")
					 .append("message=").append(message);
					
		ResponseEntity<String> forEntity = restTemplate.getForEntity(stringBuilder.toString(), String.class);
	}
	
}
