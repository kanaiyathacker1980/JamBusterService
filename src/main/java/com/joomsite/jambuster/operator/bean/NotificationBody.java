package com.joomsite.jambuster.operator.bean;

public class NotificationBody {
	public String  body;
	public long  bookVehicleId;
	public String  lockToken;
	public String  type;
	
	public NotificationBody(String body , String type) {
		this.body = body;
		this.type = type;
	}
	
	public NotificationBody(String body , String type , long bookVehicleId , String lockToken) {
		this.body = body;
		this.type = type;
		this.bookVehicleId = bookVehicleId;
		this.lockToken = lockToken;
	}

}
