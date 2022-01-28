package com.cg.nsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.entity.Student;
import com.cg.nsa.repository.IScholarshipRepository;
@Service
public class ScholarshipService implements IScholarshipService {

	@Autowired
	private IScholarshipRepository iScholarshipRepository;
	
	@Override
	public List<Scholarship> statusUpdate(Student student) {
		
//		return iScholarshipRepository.findById(scholarship.getScholarshipId()).get();
		return iScholarshipRepository.statusUpdate(student.getUserId());
	}

	@Override
	public List<Scholarship> getAllScholarships() {
		
		return iScholarshipRepository.findAll();
	}

	@Override
	public Scholarship addScholarship(Scholarship scholarship) {
		// TODO Auto-generated method stub
		return iScholarshipRepository.save(scholarship);
	}
	@Override
	public List<Scholarship> getScholarshipsByState(String state) {
		// TODO Auto-generated method stub
		return iScholarshipRepository.getScholarshipsByState(state);
	}

}
