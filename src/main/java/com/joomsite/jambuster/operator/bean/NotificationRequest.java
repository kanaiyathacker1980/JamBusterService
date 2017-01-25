package com.joomsite.jambuster.operator.bean;


public class NotificationRequest {
	public Notification notification;
	public String to;
	
	public NotificationRequest(Notification notification , String to) {
		this.notification = notification;
		this.to=to;
	}
}
