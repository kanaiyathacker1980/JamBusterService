package com.joomsite.jambuster.operator.bean;

public class Login {

	public String password;
	public String email;
	public String mobile;
	public String deviceId;
	public String deviceType;
	
	@Override
	public String toString() {
		return "Login [password=" + password + ", email=" + email
				+ ", deviceId=" + deviceId + ", deviceType=" + deviceType + "]";
	}
	
}
