package com.joomsite.jambuster.operator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joomsite.jambuster.operator.bean.VehicleCase;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.VehicleCaseService;

@RestController
@RequestMapping("/service")
public class VehicleCaseController {

	@Autowired VehicleCaseService caseService;
	
	@RequestMapping(value = "/vehicleCase", method = RequestMethod.POST)
	@ResponseBody public Response<Object> createCase(@RequestBody VehicleCase caseObj , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				caseService.createCase(caseObj);
				response = new Response<Object>("SUCCESS", "" , caseObj);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCase/vehicleCaseId/{vehicleCaseId}", method = RequestMethod.PUT)
	@ResponseBody public Response<Object> editCase(@PathVariable long vehicleCaseId  , @RequestBody VehicleCase caseObj , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				caseService.editCase(vehicleCaseId , caseObj);
				response = new Response<Object>("SUCCESS", "" , caseObj);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCase/vehicleCaseId/{vehicleCaseId}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getCaseByCaseId(@PathVariable long vehicleCaseId ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			VehicleCase c = caseService.getCaseByCaseId(vehicleCaseId);
			response = new Response<Object>("SUCCESS", "", c);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCase/vehicleNo/{vehicleNo}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getCaseByCaseId(@PathVariable String vehicleNo ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<VehicleCase> caseList = caseService.getCaseByVehicleNo(vehicleNo);
			response = new Response<Object>("SUCCESS", "", caseList);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCase/operatorId/{operatorId}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getCaseByOperatorId(@PathVariable long operatorId ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<VehicleCase> caseList = caseService.getCaseByOperatorId(operatorId);
			response = new Response<Object>("SUCCESS", "", caseList);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCases", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getAllCases(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<VehicleCase> caseList = caseService.getAllCases();
			response = new Response<Object>("SUCCESS", "", caseList);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/vehicleCase/caseOpenDate/caseOpenFromDate/{caseOpenFromDate}/caseOpenToDate/{caseOpenToDate}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getCaseByCaseOpenDate(@PathVariable String caseOpenFromDate , @PathVariable String caseOpenToDate ,
								 					HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<VehicleCase> caseList = caseService.getCaseByCaseOpenDate(caseOpenFromDate , caseOpenToDate);
			response = new Response<Object>("SUCCESS", "", caseList);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
}
