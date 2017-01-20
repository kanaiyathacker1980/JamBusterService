package com.joomsite.jambuster.operator.bean;

public class CustodyLocation {
	public long custodyLocationId;
	public String addressLine1;
	public String addressLine2;
	public String city;
	public String state;
	public String country;
	public String createdDate;
	public String createdBy;
	
	@Override
	public String toString() {
		return "CustodyLocation [custodyLocationId=" + custodyLocationId
				+ ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + "]";
	}
	
}
