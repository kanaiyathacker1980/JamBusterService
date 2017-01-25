package com.joomsite.jambuster.operator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joomsite.jambuster.operator.bean.Notification;
import com.joomsite.jambuster.operator.bean.NotificationRequest;
import com.joomsite.jambuster.operator.bean.NotificationResponse;

@Service
@EnableAsync
public class NotificationService {

	@Value(value="${notificationURL}") String notificationURL;
	@Value(value="${notificationApiKey}") String notificationApiKey;
	@Value(value="${notificationTitle}") String notificationTitle;
	
	@Async
	public NotificationResponse broadcastMessage(String deviceId, String message) {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "key="+notificationApiKey);
		RestTemplate restTemplate = new RestTemplate();
		Notification notification = new Notification(notificationTitle , message , null);
		NotificationRequest notificationRequest = new NotificationRequest(notification , deviceId);
		//notificationRequest.to = "ez5PJjjdJMs:APA91bF6ZRMarEAugtzuk992DRUp9AlD-0ZE1ROKAyeGs0jHXp0BFmRMooAayMw6WYAOH6z-ISFLe0EAbws80E-DX3eWsyYNSOm11HT-OQ6vAh6EOWkQI3j3B1GLoCGBC1zSoWxz_s6H";
		//notificationRequest.to = "eTPLGr7TqAQ:APA91bGw5Yh5JZBakG-2QZ2K53Ivw1tbeWZ6LoaOXvnQEhlrAuzFAEfbWbOnTXlM-cK9_tYgLaSLtVpZmn-xDjKivoYXJWZaD2fNK3SGr5IzVwZ7jvDwJS2MdBmLJ98lz-VBpqWCTlJk";
		HttpEntity<NotificationRequest> request  = new HttpEntity<NotificationRequest>(notificationRequest, headers);
		ResponseEntity<NotificationResponse> exchange = restTemplate.exchange(notificationURL , HttpMethod.POST, request, NotificationResponse.class);
		NotificationResponse body = exchange.getBody();
		System.out.println("Body " + body);
		return body;
	}

	
	public static void main(String[] args) {
		NotificationService n = new NotificationService();  
		n.broadcastMessage("dL0sRO2_bqw:APA91bEKcCxWHQc0jOkDPrhD9U-EU0bZ0lGahEEuxlF42uiwEWu6_IeP3b6kU-dltOXM5HmW-JSuWpoK5efW6EavE_TxI7Rh1_p-_pC1h-GJR13M4pzhb8tJBeZrFDnaEQwYqlSvtlPR" , "Hello kanaiya");
		
	}
}
