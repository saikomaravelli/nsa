package com.cg.nsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.service.IInstituteService;
import com.cg.nsa.service.IScholarshipService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ScholarshipController {

	@Autowired
	private IScholarshipService iScholarshipService;
	@Autowired
	private IInstituteService iInstituteService;

	@PostMapping("createScholarship")
	public Scholarship addScholarship(@RequestBody Scholarship scholarship) throws InvalidInstitutionException {
		scholarship.setAppStatus("Pending");
		scholarship.setApproval("Pending");
		
		String userId = iInstituteService.getInstitute(scholarship.getInstitute().getCode()).getUserId();
		Institution institute = new Institution();
		institute.setUserId(userId);
		scholarship.setInstitute(institute);
		
		return iScholarshipService.addScholarship(scholarship);
	}

//	@GetMapping("viewScholarshipStatusUpdate/{sid}")
//	public Scholarship statusUpdate(@PathVariable("sid") int scholarshipId) {
//		Scholarship scholarship = new Scholarship();
//		scholarship.setScholarshipId(scholarshipId);
//		return iScholarshipService.statusUpdate(scholarship);
//	}
	@GetMapping("viewScholarshipStatusUpdate/{sid}")
	public List<Scholarship> statusUpdate(@PathVariable("sid") String studentUserId) {
		Student student = new Student();
		student.setUserId(studentUserId);
		return iScholarshipService.statusUpdate(student);
	}
	@GetMapping("viewScholarshipsByState/{state}")
	public List<Scholarship> getScholarshipsByState(@PathVariable("state") String state){
		return iScholarshipService.getScholarshipsByState(state);
	}

	@GetMapping("viewAllScholarships")
	public List<Scholarship> getAllScholarships(){
		return iScholarshipService.getAllScholarships();
	}
}
