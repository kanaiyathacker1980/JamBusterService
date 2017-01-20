package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.OperatorRole;
import com.joomsite.jambuster.operator.repository.OperatorRoleRepository;

@Service
public class OperatorRoleService {
	
	@Autowired OperatorRoleRepository operatorRoleRepository;

	public void createOperatorRole(OperatorRole operatorRole) {
		// TODO Auto-generated method stub
		operatorRoleRepository.createOperatorRole(operatorRole);
	}

	public void createOperatorRoles(List<OperatorRole> operatorRole) {
		// TODO Auto-generated method stub
		operatorRoleRepository.createOperatorRoles(operatorRole);
		
	}
}
