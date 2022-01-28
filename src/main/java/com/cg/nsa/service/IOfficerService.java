package com.cg.nsa.service;

import java.util.List;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.entity.Officer;

import com.cg.nsa.entity.Scholarship;

public interface IOfficerService {

	Officer addOfficer(Officer officer);

	Officer editOfficer(Officer officer);

	Officer getOfficerByState(String state);

	List<Officer> getAllOfficer();

	List<Institution> reviewInstitutes(List<Institution >institutes);
	
	List<Scholarship> reviewScholarships(List<Scholarship> scholarships);

}
