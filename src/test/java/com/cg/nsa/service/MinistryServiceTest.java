package com.cg.nsa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
import com.cg.nsa.repository.IMinistryRepository;
import com.cg.nsa.repository.IScholarshipRepository;

@ContextConfiguration(classes=NsaApplication.class)
@WebMvcTest(value=MinistryService.class)
class MinistryServiceTest {

	@Autowired
	private IMinistryService iMinistryService;
	@MockBean
	private IMinistryRepository iMinistryRepository;
	
	@MockBean
	private IScholarshipRepository iScholarshipRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testGrant() {
		Scholarship scholarship=getscholarship();
		
		List<Scholarship> scholarshipsList=new ArrayList<Scholarship>();
		
		scholarshipsList.add(scholarship);
		Mockito.when(iScholarshipRepository.save(Mockito.any(Scholarship.class))).thenReturn(scholarship);
		List<Scholarship> resultScholarship=iMinistryService.grant(scholarshipsList);
		assertEquals("granted",resultScholarship.get(0).getApproval());
		
		scholarship.setAppStatus("Pending");
		scholarship.setApproval("pending");
		List<Scholarship> scholarshipsListTwo=new ArrayList<Scholarship>();
		scholarshipsListTwo.add(scholarship);
		List<Scholarship> resultScholarshipTwo=iMinistryService.grant(scholarshipsListTwo);
		
		assertNotEquals("granted",resultScholarshipTwo.get(0).getApproval());
		
		
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
		
		Student s=new Student();
		s.setUserId("std01");
		scholarship.setStudent(s);
		Institution institute=new Institution();
		institute.setCode(123);
		institute.setStatus("Approved");
		scholarship.setInstitute(institute);
		
		
		return scholarship;
	}

}
