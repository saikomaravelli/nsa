package com.cg.nsa.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.repository.IMinistryRepository;
import com.cg.nsa.repository.IScholarshipRepository;


@Service
public class MinistryService implements IMinistryService {

	@Autowired
	private IMinistryRepository iMinistryRepository;
	@Autowired
	private IScholarshipRepository iScholarshipRepository;
	
	@Override
	public List<Scholarship> grant(List<Scholarship> scholarshipList) {
		
		List<Scholarship> grantedScholarships = new ArrayList<Scholarship>();
		
		for(Scholarship scholarship: scholarshipList) {
			if("approved".equalsIgnoreCase(scholarship.getAppStatus())) {
				scholarship.setApproval("granted");
			}
			
			iScholarshipRepository.save(scholarship);
			grantedScholarships.add(scholarship);
		}
		return grantedScholarships;
	}
}
