package com.cg.nsa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.cg.nsa.NsaApplication;
import com.cg.nsa.entity.Institution;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.repository.IStudentRepository;


@ContextConfiguration(classes = NsaApplication.class)
@WebMvcTest(value = StudentService.class)
class StudentServiceTest {

	@Autowired
	private IStudentService iStudentService;
	
	@MockBean
	private IStudentRepository iStudentRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddStudent() {
		Student student=getstudent();
		Mockito.when(iStudentRepository.save(Mockito.any(Student.class))).thenReturn(student);
		Student result=iStudentService.addStudent(student);
		assertEquals(student, result);
		
	}
	@Test
	void testEditStudent() {
		
		Student student=getstudent();
		Optional<Student> o=Optional.of(student);
		Mockito.when(iStudentRepository.findById(student.getUserId())).thenReturn(o);
		Student studentCopy=student;
		studentCopy.setCity("hyd");
		Mockito.when(iStudentRepository.save(Mockito.any(Student.class))).thenReturn(studentCopy);
		assertEquals(studentCopy,iStudentService.editStudent(studentCopy));
	}

	@Test
	void testGetAllStudents() {
		Student student=getstudent();
		List<Student> studentlist=new ArrayList<Student>();
		studentlist.add(student);
		Mockito.when(iStudentRepository.findAll()).thenReturn(studentlist);
		List<Student> resultlist=iStudentService.getAllStudents();
		assertEquals(studentlist,resultlist);
	}

	@Test
	void testGetStudentsByInstitute() throws InvalidInstitutionException {
		Scholarship scholarship = getscholarship();
		Student student = getstudent();
		List<Student> studentList = new ArrayList<>();
		studentList.add(student);
		Mockito.when(iStudentRepository.fetchStudentsByInstitute(scholarship.getInstitute().getName())).thenReturn(studentList);
		
		List<Student> resultList = iStudentService.getStudentsByInstitute("CMR college");
		assertEquals(studentList, resultList);
		try {
		List<Student> resultListTwo = iStudentService.getStudentsByInstitute("Vishnu college");
		assertNotEquals(studentList, resultListTwo);
		}
		catch(InvalidInstitutionException e) {
			assertEquals("No Student found under the given institution or Invalid institution Name", e.getMessage());
		}
		
	}
	private Institution getInstitute() {
		Institution inst = new Institution();
		inst.setUserId("I1");
		inst.setPassword("I1@Pass");
		inst.setRole("institution");
		inst.setCode(123);
		inst.setCategory("Autonomous");
		inst.setType("Engineering");
		inst.setName("CMR college");
		inst.setUniversity("JNTUH");
		inst.setAddress("MedchalRoad");
		inst.setCity("Medchal");
		inst.setState("Telangana");
		inst.setYearOpen(1998);
		inst.setTelephone("08736457826");
		inst.setPrincipal("AravindKumar");
		inst.setStatus("pending");
		
		return inst;
		
	}
	private Scholarship getscholarship()
	{
		Scholarship scholarship=new Scholarship();
		
		scholarship.setScholarshipName("PM Yojana");
		scholarship.setField("Engineering");
		scholarship.setCourse("btech");
		scholarship.setCourseYear(2021);
		scholarship.setSscScore(70);
		scholarship.setHscScore(90);
		scholarship.setFamilyIncome(100000);
		scholarship.setBankName("SBI");
		scholarship.setBankIfsc("SBI123");
		scholarship.setAccountNo("12345678");
		scholarship.setAppStatus("Approved");
	
		scholarship.setStudent(getstudent());
		scholarship.setInstitute(getInstitute());
		
		
		return scholarship;
	}
	 private Student getstudent() {
	    	Student student=new Student();
	    	student.setStudentId(2);
	    	student.setFullName("varun");
	    	student.setBirthdate(LocalDate.of(2000, 9, 22));
	    	student.setGender("male");
	    	student.setMobile("9876543212");
	    	student.setEmail("varun@123");
	    	student.setAddress("adds");
	    	student.setCity("hyd");
	    	student.setAadhar("3456787899");
	    	student.setUserId("s1");
	    	student.setPassword("pass");
	    	student.setRole("student");
			
			return student;
		}

}
