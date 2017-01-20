package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.VehicleCase;
import com.joomsite.jambuster.operator.repository.VehicleCaseRepository;

@Service
public class VehicleCaseService {

	@Autowired VehicleCaseRepository vehicleCaseRepository;
	
	public void createCase(VehicleCase caseObj) {
		long caseId = vehicleCaseRepository.createCase(caseObj);
		caseObj.vehicleCaseId = caseId;
	}
	
	public List<VehicleCase> getCaseByCaseOpenDate(String fromDate , String toDate) {
		return vehicleCaseRepository.getCaseByCaseOpenDate(fromDate, toDate);
	}
	
	public VehicleCase getCaseByCaseId(long caseId) {
		return vehicleCaseRepository.getCaseByCaseId(caseId);
	}
	
	public List<VehicleCase> getCaseByVehicleNo(String vehicleNo) {
		return vehicleCaseRepository.getCaseByVehicleNo(vehicleNo);
	}

	public void editCase(long caseId, VehicleCase caseObj) {
		// TODO Auto-generated method stub
		vehicleCaseRepository.editCase(caseId , caseObj);
	}

	public List<VehicleCase> getAllCases() {
		// TODO Auto-generated method stub
		return vehicleCaseRepository.getAllCases();
	}

	public List<VehicleCase> getCaseByOperatorId(long operatorId) {
		// TODO Auto-generated method stub
		return vehicleCaseRepository.getCaseByOperatorId(operatorId);
	}

}
