package com.joomsite.jambuster.operator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.CaseAttachment;
import com.joomsite.jambuster.operator.bean.Images;

@Service
public class ImageService {

	@Autowired FileService fileService;
	@Autowired CaseAttachmentService caseAttachmentService;
	
	public List<Images> getAllImages(long caseId) {
		// TODO Auto-generated method stub
		List<Images>  retval = new ArrayList<Images>();
		List<CaseAttachment> caseAttachmentByCaseId = caseAttachmentService.getCaseAttachmentByCaseId(caseId);
		for(CaseAttachment str : caseAttachmentByCaseId) {
			byte[] reportsData = fileService.readFile(str.fileName);
			Images img = new Images();
			img.name = str.fileName;
			String split[] = str.fileName.split(Pattern.quote("."));
			img.type = split != null && split.length > 1 ? split[split.length -1] : "UnKnown" ;
			img.data = reportsData;
			retval.add(img);
		}
		return retval;
	}
	
	public static void main(String[] args) {
		String str = "joomsite.JPG";
		String[] split = str.split(Pattern.quote("."));
		System.out.println(split[split.length-1]);
	}

//	public List<Images> getAllImagesByCaseId(long caseId) {
//		List<Images>  retval = new ArrayList<Images>();
//		List<CaseAttachment> caseAttachmentByCaseId = caseAttachmentService.getCaseAttachmentByCaseId(caseId);
//		for(CaseAttachment str : caseAttachmentByCaseId) {
//			byte[] reportsData = fileService.readFile(str.fileName);
//			Images img = new Images();
//			img.name = str.fileName;
//			String split[] = str.fileName.split(Pattern.quote("."));
//			img.type = split != null && split.length > 1 ? split[split.length -1] : "UnKnown" ;
//			img.data = reportsData;
//			retval.add(img);
//		}
//		return retval;
//	}

	public List<Images> getAttachmentbyCaseId(long caseId) {
		List<Images>  retval = new ArrayList<Images>();
		List<CaseAttachment> caseAttachmentByCaseId = caseAttachmentService.getCaseAttachmentByCaseId(caseId);
		for(CaseAttachment str : caseAttachmentByCaseId) {
			byte[] reportsData = fileService.readFile(str.fileName);
			Images img = new Images();
			img.name = str.fileName;
			String split[] = str.fileName.split(Pattern.quote("."));
			img.type = split != null && split.length > 1 ? split[split.length -1] : "UnKnown" ;
			img.data = reportsData;
			retval.add(img);
		}
		return retval;
	} 
	

}
