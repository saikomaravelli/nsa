package com.cg.nsa.service;

import java.util.List;

import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;

public interface IStudentService {

	Student addStudent(Student student);

	Student editStudent(Student student);

	List<Student> getAllStudents();

	List<Student> getStudentsByInstitute(String name) throws InvalidInstitutionException;
	
	Student getStudentbyUserId(String userId);
	
}
