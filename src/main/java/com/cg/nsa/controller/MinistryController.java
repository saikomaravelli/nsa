package com.cg.nsa.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.nsa.entity.Ministry;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.service.IMinistryService;
import com.cg.nsa.service.IScholarshipService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MinistryController {

	@Autowired
	private IMinistryService iMinistryService;
	@Autowired
	private IScholarshipService iScholarshipService;
	
	@PutMapping("grantScholarship")
	public List<Scholarship> grant(){
		
		Ministry ministry = new Ministry();
		ministry.setPortfolio("Education Ministry");
		
		List<Scholarship> scholarshipList = iScholarshipService.getAllScholarships();
		return iMinistryService.grant(scholarshipList);
	}

}





