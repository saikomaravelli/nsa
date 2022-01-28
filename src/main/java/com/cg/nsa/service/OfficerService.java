package com.cg.nsa.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.entity.Officer;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.repository.IOfficerRepository;
import com.cg.nsa.repository.IScholarshipRepository;


@Service
public class OfficerService implements IOfficerService{

	@Autowired
	private IOfficerRepository iOfficerRepository;

	@Autowired
	private IScholarshipRepository iScholarshipRepository;

	@Override
	public Officer addOfficer(Officer officer) {
		// TODO Auto-generated method stub
		return iOfficerRepository.save(officer);
	}

	@Override
	public Officer editOfficer(Officer officer) {
		// TODO Auto-generated method stub
		Officer fetchedOfficer=iOfficerRepository.findById(officer.getUserId()).get();
		if(fetchedOfficer!=null)
		{
			if(officer.getPassword() != null) {
				fetchedOfficer.setPassword(officer.getPassword());
			}
			if(officer.getName()!=null)
			{
				fetchedOfficer.setName(officer.getName());
			}
			if(officer.getState()!=null)
			{
				fetchedOfficer.setState(officer.getState());
			}
		}
		return iOfficerRepository.save(fetchedOfficer);

	}

	@Override
	public Officer getOfficerByState(String state) {
		// TODO Auto-generated method stub
		return iOfficerRepository.fetchOfficerByState(state);
	}

	@Override
	public List<Officer> getAllOfficer() {
		// TODO Auto-generated method stub
		List<Officer> officersList=iOfficerRepository.findAll();
		return officersList;
	}

	@Override
	public List<Institution> reviewInstitutes(List<Institution> institutes)
	{
		List<Institution> reviewedInstitutes=new ArrayList<Institution>();
		for(Institution institute:institutes)
		{
			if("government".equalsIgnoreCase(institute.getCategory())||"autonomous".equalsIgnoreCase(institute.getCategory())||"private".equalsIgnoreCase(institute.getCategory()))
			{
				institute.setStatus("Approved");
			}
			else
			{
				institute.setStatus("Rejected");
			}
			reviewedInstitutes.add(institute);
		}
		return reviewedInstitutes;
	}

	@Override
	public List<Scholarship> reviewScholarships(List<Scholarship> scholarships) {
		// TODO Auto-generated method stub
		List<Scholarship> reviewedScholarships=new ArrayList<Scholarship>();
		for(Scholarship scholarship:scholarships)
		{
			if(scholarship.getSscScore()>=60 && scholarship.getHscScore()>=60 && scholarship.getFamilyIncome() <= 100000 && scholarship.getInstitute().getStatus().equalsIgnoreCase("approved") && (!scholarship.getApproval().equalsIgnoreCase("Rejected")))
			{
				scholarship.setAppStatus("Approved" );
			}
			else
			{
				scholarship.setAppStatus("Rejected");
				scholarship.setApproval("Rejected");
			}
			iScholarshipRepository.save(scholarship);
			reviewedScholarships.add(scholarship);
		}
		return reviewedScholarships;
	}


}
