package com.joomsite.jambuster.operator.bean;

import java.math.BigDecimal;

public class VehicleCase {
	  public long vehicleCaseId;
	  public long operatorId;
	  public String vehicleNo;
	  public String vehicleType;
	  public String caseDesc;
	  public String caseOpenDate;
	  public String caseOpenTime;
	  public String caseCloseDate;
	  public String contactNumber;
	  public BigDecimal charges;
	  public String pickupLocationLat;
	  public String pickupLocationLon;
	  public String pickupLocation;
	  public long custodyLocationId;
	  public String status;
	  
	  @Override
	  public String toString() {
		return "VehicleCase [vehicleCaseId=" + vehicleCaseId + ", operatorId="
				+ operatorId + ", vehicleNo=" + vehicleNo + ", vehicleType="
				+ vehicleType + ", caseDesc=" + caseDesc + ", caseOpenDate="
				+ caseOpenDate + ", caseOpenTime=" + caseOpenTime
				+ ", caseCloseDate=" + caseCloseDate + ", contactNumber="
				+ contactNumber + ", charges=" + charges
				+ ", pickupLocationLat=" + pickupLocationLat
				+ ", pickupLocationLon=" + pickupLocationLon
				+ ", pickupLocation=" + pickupLocation + ", custodyLocationId="
				+ custodyLocationId + ", status=" + status + "]";
	}
}
