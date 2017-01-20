package com.joomsite.jambuster.operator.controller;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joomsite.jambuster.operator.bean.Images;
import com.joomsite.jambuster.operator.bean.Response;
import com.joomsite.jambuster.operator.service.FileService;
import com.joomsite.jambuster.operator.service.ImageService;

@RestController
@RequestMapping("/service")
public class FileController {
	
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired FileService fileService;
	@Autowired ImageService imageService;
//	@Autowired LoginService loginService;
	
	@RequestMapping(value = "/image/download/{imageName:.+}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadImage(
			@RequestHeader(value="Authorization") String auth , 
			@PathVariable String imageName , 
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
//			if(Utility.isAuth(auth, loginService)) {
				    String filename = URLDecoder.decode(httpRequest.getPathInfo().substring(1), "UTF-8");
				    byte[] reportsData = fileService.readFile(imageName);
					httpResponse.setContentType("application/octet-stream");
					httpResponse.setContentLength((int) reportsData.length);
					httpResponse.setHeader("Content-Disposition", "attachment; filename=" + filename);
					OutputStream outStream = httpResponse.getOutputStream();
					outStream.write(reportsData);
					outStream.close();
//			} else {
//				httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			}
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getAllImageNames( @RequestHeader(value="Authorization") String auth , 
							   HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response = null;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
//			if(Utility.isAuth(auth, loginService)) {
				List<String> allImageNames = fileService.getAllImageNames();
				response = new Response<Object>("SUCCESS", "" , allImageNames);
//			} else {
//				httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//				response = new Response<Object>("FAILURE", "INVALID HEADER AUTHENTICATION");
//			}
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/getAttachment/vehicleCaseId/{vehicleCaseId}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Object> getAttachmentbyCaseId(@PathVariable long vehicleCaseId ,
							   HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Response<Object> response = null;
		try {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		    httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,accept");
//			if(Utility.isAuth(auth, loginService)) {
				List<Images> allImageNames = imageService.getAttachmentbyCaseId(vehicleCaseId);
				response = new Response<Object>("SUCCESS", "" , allImageNames);
//			} else {
//				httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//				response = new Response<Object>("FAILURE", "INVALID HEADER AUTHENTICATION");
//			}
		} catch(Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = new Response<Object>("FAILED",e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/addAttachment/vehicleCaseId/{vehicleCaseId}", method = RequestMethod.POST)
	@ResponseBody
    public String addCaseAttachment(@PathVariable long vehicleCaseId ,@RequestBody Images images ) {
		logger.info("Enter uploadCourseOutline - > {}" , images);
		System.out.println("imageName" + images);
		String retVal = null;
		if (images != null) {
            try {
            	
//            	if(Utility.isAuth(auth, loginService)) {
//	                byte[] bytes = images.data;
	                fileService.addCaseAttachment(vehicleCaseId , images);
//            	} else {
//    			}
            } catch (Exception e) {
            	e.printStackTrace();
            	logger.error("Error in method addAttachment ", e.getMessage());
            }
		 } else {
	     }
		logger.info("Exit uploadCourseOutline - > {}" );
	    return null;
    }
	
	
}
