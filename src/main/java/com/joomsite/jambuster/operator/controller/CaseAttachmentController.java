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

import com.joomsite.jambuster.operator.bean.CaseAttachment;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.CaseAttachmentService;

@RestController
@RequestMapping("/service")
public class CaseAttachmentController {
	
	@Autowired CaseAttachmentService caseAttachmentService;
	
//	@RequestMapping(value = "/caseAttachment/caseId/{caseId}/fileName/{fileName}", method = RequestMethod.POST)
//	@ResponseBody public Response<Object> createCaseAttachment(@PathVariable long caseId  , @PathVariable String fileName   , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
//		Response<Object> response;
//		try {
//				caseAttachmentService.createCaseAttachment(caseId, fileName);
//				response = new Response<Object>("SUCCESS", "" );
//		} catch(Exception e) {
//			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			response = new Response<Object>("FAILED",e.getMessage());
//		}
//		return response;
//	}
	
	@RequestMapping(value = "/caseAttachment/caseAttachmentId/{caseAttachmentId}", method = RequestMethod.PUT)
	@ResponseBody public Response<Object> editCaseAttachment(@PathVariable long caseAttachmentId  , @RequestBody CaseAttachment ca , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				caseAttachmentService.editCaseAttachment(caseAttachmentId , ca);
				response = new Response<Object>("SUCCESS", "" , ca);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/caseAttachment/caseAttachmentId/{caseAttachmentId}", method = RequestMethod.GET)
	@ResponseBody public Response<Object> getCaseAttachmentByCaseAttachmentId(@PathVariable long caseAttachmentId , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				CaseAttachment ca = caseAttachmentService.getCaseAttachmentByCaseAttachmentId(caseAttachmentId);
				response = new Response<Object>("SUCCESS", "" , ca);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/caseAttachment/caseId/{caseId}", method = RequestMethod.GET)
	@ResponseBody public Response<Object> getCaseAttachmentByCaseId(@PathVariable long caseId , HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
				List<CaseAttachment> caList = caseAttachmentService.getCaseAttachmentByCaseId(caseId);
				response = new Response<Object>("SUCCESS", "" , caList);
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
}
