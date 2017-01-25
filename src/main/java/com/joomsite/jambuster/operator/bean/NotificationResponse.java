package com.joomsite.jambuster.operator.bean;

import java.util.List;

public class NotificationResponse {
	public String multicast_id;
	public String success;
	public String failure;
	public String canonical_ids;
	public List<NotificationResponseResult> results; 
}
