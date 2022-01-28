package com.cg.nsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.entity.Officer;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.service.IInstituteService;
import com.cg.nsa.service.IOfficerService;
import com.cg.nsa.service.IScholarshipService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OfficerController {
	
	@Autowired
	private IOfficerService iOfficerService;  
	@Autowired
	private IInstituteService iInstituteService;
	@Autowired
	private IScholarshipService iScholarshipService;
	
	//Method to create a officer profile with attributes name and state.
	@PostMapping("createofficerprofile")
	public Officer createOfficerProfile(@RequestBody Officer officer)
	{
		return iOfficerService.addOfficer(officer);
		
		
	}
	
	//Method to edit officer profile with id=state;
	@PutMapping("editofficerprofile/{id}")
	public Officer editOfficerProfile(@PathVariable("id") String userId,@RequestBody Officer officer)
	{
		officer.setUserId(userId);
		return iOfficerService.editOfficer(officer);
		
	}
	
	//Method to get officer details by providing state as id
	@GetMapping("viewofficerbystate/{id}")
	public Officer viewOfficerByState(@PathVariable("id") String state)
	{
		Officer officer=new Officer();
		officer.setState(state);
		return iOfficerService.getOfficerByState(officer.getState());
		
	}
	
	//Method to view list of all officers
	@GetMapping("viewallofficers")
	public List<Officer> viewAllOfficers()
	{
		List<Officer> officerList=iOfficerService.getAllOfficer();
		return officerList;
	}
	
	@PutMapping("reviewInstitute/{name},{state}")
	public List<Institution> reviewInstitutes(@PathVariable("name") String name, @PathVariable("state") String state)
	{
		Officer officer = new Officer();
		officer.setName(name);
		officer.setState(state);
		List<Institution> institutes=iInstituteService.getInstitutesByState(officer.getState());
		List<Institution> reviewedInstitutes=iOfficerService.reviewInstitutes(institutes);
		for(Institution institute:institutes)
		{
			iInstituteService.editInstitute(institute);
		}
		return reviewedInstitutes;
	}

	
	@PutMapping("reviewScholarship/{name},{state}")
	public List<Scholarship> reviewScholarship(@PathVariable("name") String name, @PathVariable("state") String state)
	{
		Officer officer = new Officer();
		officer.setName(name);
		officer.setState(state);
		List<Scholarship> scholarships=iScholarshipService.getScholarshipsByState(state);
//		List<Scholarship> stateScholarships = new ArrayList<>();
//		for(Scholarship scholar : scholarships) {
//			if(scholar.getInstitute().getState().equalsIgnoreCase(officer.getState())) {
//				stateScholarships.add(scholar);
//			}
//		}
		return iOfficerService.reviewScholarships(scholarships);
		
		
	}
}
