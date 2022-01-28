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

import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.service.IStudentService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {

	@Autowired
	private IStudentService iStudentService;

	@PostMapping("createStudent")
	public Student addStudent(@RequestBody Student student) {

		return iStudentService.addStudent(student);

	}
	@PutMapping("editStudent")
	public Student editStudent(@RequestBody Student student) {

		return iStudentService.editStudent(student);

	}
	@GetMapping("viewAllStudents")
	public List<Student> getAllStudents(){
		List<Student> studentList=iStudentService.getAllStudents();
		return studentList;
	}
	//to be completed...
	
	  @GetMapping("viewStudentsByInstitute/{iName}")
	  public List<Student> getStudentsByInstitute(@PathVariable("iName") String name) throws InvalidInstitutionException { 
	  List<Student> studentsListByInstitute=iStudentService.getStudentsByInstitute(name); 
	  return studentsListByInstitute; 
	  }
	  
	  @GetMapping("getStudentbyUserId/{id}")
		public Student getStudentbyId(@PathVariable("id") String userId){
			
			return iStudentService.getStudentbyUserId(userId);
			 
		}
}
