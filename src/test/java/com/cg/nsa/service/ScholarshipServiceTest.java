package com.cg.nsa.service;

import static org.junit.jupiter.api.Assertions.*;

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
import com.cg.nsa.repository.IScholarshipRepository;

@ContextConfiguration(classes = NsaApplication.class)
@WebMvcTest(value = ScholarshipService.class)
class ScholarshipServiceTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Autowired
	private IScholarshipService iScholarshipService;
	
	@MockBean
	private IScholarshipRepository iScholarshipRepository;

	@Test
	void testStatusUpdate() {
		Scholarship scholarship = getScholarship();
		List<Scholarship> scholarshipList = new ArrayList<>();
		scholarshipList.add(scholarship);
		Optional<Scholarship> op = Optional.of(scholarship);
//		Mockito.when(iScholarshipRepository.findById(scholarship.getStudent().getUserId()).thenReturn(op);
		Mockito.when(iScholarshipRepository.statusUpdate(scholarship.getStudent().getUserId())).thenReturn(scholarshipList);
		List<Scholarship> resultScholarship = iScholarshipService.statusUpdate(scholarship.getStudent());
		
		assertEquals(scholarshipList,resultScholarship);
		
	}


	@Test
	void testGetAllScholarships() {
		Scholarship  scholarship = getScholarship();
		List<Scholarship> scholarshipList = new ArrayList<Scholarship>();
		scholarshipList.add(scholarship);
		Mockito.when(iScholarshipRepository.findAll()).thenReturn(scholarshipList);
		
		List<Scholarship> resultScholarshipList = iScholarshipService.getAllScholarships();
		
		assertEquals(scholarshipList, resultScholarshipList);
	}

	@Test
	void testAddScholarship() {
		Scholarship scholarship = getScholarship();
		Mockito.when(iScholarshipRepository.save(Mockito.any(Scholarship.class))).thenReturn(scholarship);
		
		Scholarship resultScholarship = iScholarshipService.addScholarship(scholarship);
		
		assertEquals(scholarship, resultScholarship);
		
	}
	private Institution getInstitute()
	{
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
	private Scholarship getScholarship()
	{
		Scholarship scholarship=new Scholarship();
		scholarship.setScholarshipId(1);
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
		Student s=new Student();
		s.setUserId("std01");
		scholarship.setStudent(s);
		scholarship.setInstitute(getInstitute());
		
		
		return scholarship;
	}

}
