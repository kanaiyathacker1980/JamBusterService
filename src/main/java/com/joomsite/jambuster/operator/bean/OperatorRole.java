package com.joomsite.jambuster.operator.bean;

public class OperatorRole {
	
	public long operatorRolId;
	public long operatorId;
	public long roleId;
	
	@Override
	public String toString() {
		return "OperatorRole [operatorRolId=" + operatorRolId + ", operatorId="
				+ operatorId + ", roleId=" + roleId + "]";
	}
	
}
