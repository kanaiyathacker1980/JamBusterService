package com.joomsite.jambuster.operator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joomsite.jambuster.operator.bean.CaseAttachment;
import com.joomsite.jambuster.operator.repository.CaseAttachmentRepository;

@Service
public class CaseAttachmentService {

	@Autowired CaseAttachmentRepository caseAttachmentRepository;
	
	public void createCaseAttachment(long caseId , String fileName , String filePath) {
		caseAttachmentRepository.createCaseAttachment(caseId, fileName, filePath);
	}
	
	public void editCaseAttachment(long caseAttachmentId , CaseAttachment ca) {
		caseAttachmentRepository.editCaseAttachment(caseAttachmentId, ca);
	}
	
	public CaseAttachment getCaseAttachmentByCaseAttachmentId(long caseAttachmentId) {
		return caseAttachmentRepository.getCaseAttachmentByCaseAttachmentId(caseAttachmentId);
	}
	
	public List<CaseAttachment> getCaseAttachmentByCaseId(long caseId) {
		return caseAttachmentRepository.getCaseAttachmentByCaseId(caseId);
	}

}
