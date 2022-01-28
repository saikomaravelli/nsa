package com.cg.nsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentRepository iStudentRepository;

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return iStudentRepository.save(student);
	}

	@Override
	public Student editStudent(Student student) {
		// TODO Auto-generated method stub
		Student fetchedStudent=iStudentRepository.findById(student.getUserId()).get();
		if(fetchedStudent!=null) {
			if(student.getPassword() != null) {
				fetchedStudent.setPassword(student.getPassword());
			}
			if(student.getStudentId()>0) {
				fetchedStudent.setStudentId(student.getStudentId());
			}
			if(student.getAadhar()!=null) {
				fetchedStudent.setAadhar(student.getAadhar());
			}
			if(student.getBirthdate()!=null) {
				fetchedStudent.setBirthdate(student.getBirthdate());
			}
			if(student.getCity()!=null) {
				fetchedStudent.setCity(student.getCity());
			}
			if(student.getEmail()!=null) {
				fetchedStudent.setEmail(student.getEmail());
			}
			if(student.getFullName()!=null) {
				fetchedStudent.setFullName(student.getFullName());
			}
			if(student.getGender()!=null) {
				fetchedStudent.setGender(student.getGender());
			}
			if(student.getMobile()!=null) {
				fetchedStudent.setMobile(student.getMobile());
			}
			if(student.getAddress()!=null) {
				fetchedStudent.setAddress(student.getAddress());
			}
			return iStudentRepository.save(fetchedStudent);
		}

		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList=iStudentRepository.findAll();
		return studentList;
	}


	@Override 
	public List<Student> getStudentsByInstitute(String name) throws InvalidInstitutionException{ 
		List<Student> studentListByInstitute=iStudentRepository.fetchStudentsByInstitute(name);
		if(studentListByInstitute == null || studentListByInstitute.size() == 0) {
			throw new InvalidInstitutionException("No Student found under the given institution or Invalid institution Name");
		}
		return studentListByInstitute; 
	}


	public Student getStudentbyUserId(String userId) {
		return iStudentRepository.fetchStudentByUserId(userId);
		
	}

}
