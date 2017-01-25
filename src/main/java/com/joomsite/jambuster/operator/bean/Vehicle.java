package com.joomsite.jambuster.operator.bean;

public class Vehicle {

	public long vehicleId;
	public long customerId;
	public String vehicleType;
	public String vehicleNo;
	public String pucNo;
	public String pucStartDate;
	public String pucEndDate;
	public String insuranceNo;
	public String insuranceStartDate;
	public String insuranceEndDate;
	public String createdDate;
	
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", customerId=" + customerId
				+ ", vehicleType=" + vehicleType + ", vehicleNo=" + vehicleNo
				+ ", pucNo=" + pucNo + ", pucStartDate=" + pucStartDate
				+ ", pucEndDate=" + pucEndDate + ", insuranceNo=" + insuranceNo
				+ ", insuranceStartDate=" + insuranceStartDate
				+ ", insuranceEndDate=" + insuranceEndDate + ", createdDate="
				+ createdDate + "]";
	}
}
