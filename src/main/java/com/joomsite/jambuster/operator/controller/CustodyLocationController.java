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

import com.joomsite.jambuster.operator.bean.CustodyLocation;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.CustodyLocationService;

@RestController
@RequestMapping("/service")
public class CustodyLocationController {

	@Autowired CustodyLocationService custodyLocationService;
	
	@RequestMapping(value = "/custodyLocation", method = RequestMethod.POST)
	@ResponseBody public Response<Object> createCustodyLocation(@RequestBody CustodyLocation cl , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				custodyLocationService.createCustodyLocation(cl);
				response = new Response<Object>("SUCCESS", "" , cl);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    	httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		return response;
	}
	
	@RequestMapping(value = "/custodyLocation/custodyLocationId/{custodyLocationId}", method = RequestMethod.PUT)
	@ResponseBody public Response<Object> editCustodyLocation(@PathVariable long custodyLocationId ,@RequestBody CustodyLocation cl , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				custodyLocationService.editCustodyLocation(custodyLocationId , cl);
				response = new Response<Object>("SUCCESS", "" , cl);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    	httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		return response;
	}
	
	@RequestMapping(value = "/custodyLocation/custodyLocationId/{custodyLocationId}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getCaseByCaseId(@PathVariable long custodyLocationId ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			CustodyLocation cl = custodyLocationService.getCustodyLocationByCustodyLocationId(custodyLocationId);
			response = new Response<Object>("SUCCESS", "", cl);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/custodyLocation", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getAllCustodyLocation(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<CustodyLocation> allCustodyLocation = custodyLocationService.getAllCustodyLocation();
			response = new Response<Object>("SUCCESS", "", allCustodyLocation);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
}
