package com.joomsite.jambuster.operator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joomsite.jambuster.operator.bean.OperatorRole;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.OperatorRoleService;

@RestController
@RequestMapping("/service")
public class OperatorRoleController {

	@Autowired OperatorRoleService operatorRoleService;
	
	@RequestMapping(value = "/operatorRole", method = RequestMethod.POST)
	@ResponseBody public Response<Object> createOperatorRole(@RequestBody OperatorRole operatorRole , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				operatorRoleService.createOperatorRole(operatorRole);
				response = new Response<Object>("SUCCESS", "" , operatorRole);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
//	@RequestMapping(value = "/operatorRole", method = RequestMethod.POST)
//	@ResponseBody public Response<Object> createOperatorRoles(@RequestBody List<OperatorRole> operatorRole , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
//		Response<Object> response;
//		try {
//				operatorRoleService.createOperatorRoles(operatorRole);
//				response = new Response<Object>("SUCCESS", "" , operatorRole);
//		} catch(Exception e) {
//			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			response = new Response<Object>("FAILED",e.getMessage());
//		}
//		return response;
//	}
}
