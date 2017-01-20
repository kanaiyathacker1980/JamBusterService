package com.joomsite.jambuster.operator.bean;


public class ChangePassword {
	public String newPin;
	public String oldPin;
	@Override
	public String toString() {
		return "ChangePassword [newPin=" + newPin + ", oldPin=" + oldPin + "]";
	}
}
