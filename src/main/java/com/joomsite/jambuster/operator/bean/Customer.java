package com.joomsite.jambuster.operator.bean;

import java.util.List;

public class Customer {

	public long customerId;
//	@Pattern(regexp="\\w+")
	public String firstName;
	
//	@Pattern(regexp="[a-z]{3,6}")
	public String middleName;

//	@Pattern(regexp="[a-z]{3,6}")
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
	public List<Vehicle> vehicles;
	public String drivingLicenceNumber;
	public String drivingLicenceStartDate;
	public String drivingLicenceEndDate;
	public String deviceId;
	public String deviceType;
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", country=" + country
				+ ", email=" + email + ", mobile=" + mobile + ", status="
				+ status + ", createdDate=" + createdDate + ", vehicles="
				+ vehicles + "]";
	}
	
}
