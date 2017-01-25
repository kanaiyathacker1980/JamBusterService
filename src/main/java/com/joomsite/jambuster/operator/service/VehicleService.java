package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.Vehicle;
import com.joomsite.jambuster.operator.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired VehicleRepository vehicleRepository;
	
	public Vehicle getVehicleByVehicleNo(String vehicleNo) {
		return vehicleRepository.getVehicleByVehicleNo(vehicleNo);
	}
	
	public Vehicle getVehicleByPucNo(String pucNo) {
		return vehicleRepository.getVehicleByPucNo(pucNo);
	}
	
	public Vehicle getVehicleByVehicleId(long vehicleId) {
		return vehicleRepository.getVehicleByVehicleId(vehicleId);
	}
	
	public List<Vehicle> getVehiclesByCustomerId(long customerId) {
		return vehicleRepository.getVehiclesByCustomerId(customerId);
	}


}
