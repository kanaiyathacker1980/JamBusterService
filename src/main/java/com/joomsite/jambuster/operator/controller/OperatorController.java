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

import com.joomsite.jambuster.operator.bean.ChangePassword;
import com.joomsite.jambuster.operator.bean.Login;
import com.joomsite.jambuster.operator.bean.Operator;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.OperatorService;

@RestController
@RequestMapping("/service")
public class OperatorController {

	@Autowired OperatorService operatorService;
	
	@RequestMapping(method = RequestMethod.OPTIONS, value="/**")
	public void manageOptions(HttpServletResponse httpResponse)
	{
		System.out.println("****************");
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");
	}
	
    @RequestMapping(method = RequestMethod.GET, value="/operator/login")
    @ResponseBody public Response<Object> loginOptions(HttpServletResponse httpResponse)
    {
	   httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	   httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
	   /*  httpResponse.addHeader("Access-Control-Allow-Origin", "http://LIVLT0245966");
	   httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");*/
	   return new Response<Object>("SUCCESS");
    }
	
    @RequestMapping(value = "/operator/login", method = RequestMethod.OPTIONS)
	@ResponseBody public void optionLogin(@RequestBody Login login , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
	    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
	}
    
	@RequestMapping(value = "/operator/login", method = RequestMethod.POST)
	@ResponseBody public Response<Object> login(@RequestBody Login login , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
	    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
		response = operatorService.login(login);
		return response;
	}
	
	@RequestMapping(value = "/operator/operatorId/{operatorId}", method = RequestMethod.PUT)
	@ResponseBody
	public Response<Object> editCustomer(@PathVariable long operatorId , @RequestBody Operator operator,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			operatorService.editOperator(operatorId , operator);
			response = new Response<Object>("SUCCESS", "", operator);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/operator/mobile/{mobile}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getOperatorByMobile(@PathVariable String mobile ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			Operator operator = operatorService.getOperatorByMobile(mobile);
			response = new Response<Object>("SUCCESS", "", operator);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/operator/operatorId/{operatorId}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getOperatorByOperatorId(@PathVariable long operatorId ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			Operator operator = operatorService.getOperatorByOperatorId(operatorId);
			response = new Response<Object>("SUCCESS", "", operator);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/operator/status/{status}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getOperatorByStatus(@PathVariable String status ,
								 		HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			List<Operator> operator = operatorService.getOperatorByStatus(status);
			response = new Response<Object>("SUCCESS", "", operator);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}   
		return response;
	}
	
	@RequestMapping(value = "/forgetPin/mobile/{mobile}", method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> forgetPin(@PathVariable String mobile , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response = null;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			operatorService.forgetPin(mobile);
			response = new Response<Object>("SUCCESS", "", null);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/changePassword/operatorId/{operatorId}", method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> changePassword(@PathVariable long operatorId , @RequestBody  ChangePassword changePassword  ,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response = null;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			operatorService.changePassword(operatorId , changePassword);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/changePassword/mobile/{mobile}", method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> changePassword(@PathVariable String mobile , @RequestBody  ChangePassword changePassword  ,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response = null;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
			operatorService.changePasswordByMobile(mobile , changePassword);
			response = new Response<Object>("SUCCESS", "", null);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
}