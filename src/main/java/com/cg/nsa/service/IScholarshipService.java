package com.cg.nsa.service;

import java.util.List;

import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.entity.Student;

public interface IScholarshipService {

	List<Scholarship> statusUpdate(Student student);

	List<Scholarship> getAllScholarships();

	Scholarship addScholarship(Scholarship scholarship);
	
	List<Scholarship> getScholarshipsByState(String state);
}
