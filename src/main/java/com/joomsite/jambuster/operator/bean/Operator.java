package com.joomsite.jambuster.operator.bean;


public class Operator {

	public long operatorId;
	public String firstName;
	public String middleName;
	public String lastName;
	public String dateOfBirth;
	public String addressLine1;
	public String addressLine2;
	public String city;
	public String state;
	public String country;
	public String email;
	public String mobile;
	public String status;
	public String createdDate;
	@Override
	public String toString() {
		return "Operator [operatorId=" + operatorId + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", country=" + country
				+ ", email=" + email + ", mobile=" + mobile + ", status="
				+ status + ", createdDate=" + createdDate + "]";
	}
}
