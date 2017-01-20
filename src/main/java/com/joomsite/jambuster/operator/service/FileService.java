package com.joomsite.jambuster.operator.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


//import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.Images;

@Service
public class FileService {
	
	@Autowired CaseAttachmentService caseAttachmentService;
	
	public String saveFile(long caseId , Images images) {
		// TODO Auto-generated method stub
		String filePath = System.getenv("OPENSHIFT_DATA_DIR");
		System.out.println("**************************** " + filePath);
		if(StringUtils.isEmpty(filePath)) {
			filePath = "C:\\Temp\\";
		}
		System.out.println("**************************** " + filePath + images.name);
		FileOutputStream out  = null;
		try {
			File file = new File(filePath + images.name);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new FileOutputStream(filePath + images.name);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.write(images.data);
					out.close();
				}
			} catch(Exception e) {e.printStackTrace();}
		}
		caseAttachmentService.createCaseAttachment(caseId, images.name, filePath);
		return filePath + images.name;
	}

	public byte[] readFile(String fileName) {
		byte[] ratVal= null;
		String filePath = System.getenv("OPENSHIFT_DATA_DIR");
		if(StringUtils.isEmpty(filePath)) {
			filePath = "C:\\Temp\\";
		}
		fileName = filePath + fileName;
		try {
			System.out.println(" ********* fileName = " + fileName);
			ratVal = org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(fileName));
		} catch (Exception e) {throw new RuntimeException(e.getMessage());}
		return ratVal;
	}

	public List<String> getAllImageNames() {
		List<String> retVal = new ArrayList<String>();
		String filePath = System.getenv("OPENSHIFT_DATA_DIR");
		if(StringUtils.isEmpty(filePath)) {
			filePath = "C:\\Temp\\";
		}
		File f = new File(filePath);
		if(f != null) {
			File[] listFiles = f.listFiles();
			if(listFiles != null) {
				for(File file : listFiles) {
					System.out.println(file.getName());
					retVal.add(file.getName());
				}
			}
		}
		return retVal;
	}
	
	public String addCaseAttachment(long vehicleCaseId, Images images) {
		// TODO Auto-generated method stub
		String filePath = System.getenv("OPENSHIFT_DATA_DIR");
		System.out.println("**************************** " + filePath);
		if(StringUtils.isEmpty(filePath)) {
			filePath = "C:\\Temp\\";
		}
		System.out.println("**************************** " + filePath + images.name);
		FileOutputStream out  = null;
		try {
			File file = new File(filePath + images.name);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new FileOutputStream(filePath + images.name);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.write(images.data);
					out.close();
				}
			} catch(Exception e) {e.printStackTrace();}
		}
		caseAttachmentService.createCaseAttachment(vehicleCaseId, images.name, filePath);
		return filePath + images.name;
		
	}
	
	public static void main(String[] args) {
		FileService s = new FileService();
		s.getAllImageNames();
	}
}
