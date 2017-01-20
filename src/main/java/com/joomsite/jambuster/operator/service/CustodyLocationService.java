package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.CustodyLocation;
import com.joomsite.jambuster.operator.repository.CustodyLocationRepository;

@Service
public class CustodyLocationService {

	@Autowired CustodyLocationRepository custodyLocationRepository;
	
	public void createCustodyLocation(CustodyLocation cl) {
		// TODO Auto-generated method stub
		long custodyLocationId = custodyLocationRepository.createCustodyLocation(cl);
		cl.custodyLocationId = custodyLocationId;
	}
	
	public List<CustodyLocation> getAllCustodyLocation() {
		return custodyLocationRepository.getAllCustodyLocation();
	}
	
	public CustodyLocation getCustodyLocationByCustodyLocationId(long custodyLocationId) {
		return custodyLocationRepository.getCustodyLocationByCustodyLocationId(custodyLocationId);
	}

	public void editCustodyLocation(long custodyLocationId, CustodyLocation cl) {
		custodyLocationRepository.editCustodyLocation(custodyLocationId , cl);
	}

}
