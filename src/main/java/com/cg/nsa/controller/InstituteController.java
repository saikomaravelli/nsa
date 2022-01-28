package com.cg.nsa.controller;

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
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.service.IInstituteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InstituteController {

	@Autowired
	private IInstituteService iInstituteService;
	
	@PostMapping("createInstitute")
	public Institution addInstitute(@RequestBody Institution institute) {
		
		return iInstituteService.addInstitute(institute);
	}
	
	@PutMapping("editInstitute/{id}")
	public Institution editInstitute(@PathVariable("id") String userId, @RequestBody Institution institute) {
		
		institute.setUserId(userId);
		return iInstituteService.editInstitute(institute);
	}
	
	@GetMapping("viewInstituteStatusUpdate/{id}")
	public Institution statusUpdate(@PathVariable("id") String userId) {
		Institution institute = new Institution();
		institute.setUserId(userId);
		return iInstituteService.statusUpdate(institute);
	}
	
	@GetMapping("viewInstituteByCode/{id}")
	public Institution getInstitute(@PathVariable("id") int code)  throws InvalidInstitutionException {
		return iInstituteService.getInstitute(code);
	}
	
	@GetMapping("viewAllInstitutes")
	public List<Institution> getAllInstitutes() {
		return iInstituteService.getAllInstitutes();
	}
	
	@GetMapping("viewInstitutesByState/{state}")
	public List<Institution> getInstitutesByState(@PathVariable("state") String state) {
		return iInstituteService.getInstitutesByState(state);
	}
}
