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
import com.cg.nsa.entity.Officer;
import com.cg.nsa.entity.Scholarship;
import com.cg.nsa.entity.Student;
import com.cg.nsa.repository.IOfficerRepository;
import com.cg.nsa.repository.IScholarshipRepository;

@ContextConfiguration(classes = NsaApplication.class)
@WebMvcTest(value=OfficerService.class)
class OfficerServiceTest {


	@Autowired
	private	 IOfficerService iOfficerService;

	@MockBean
	private IOfficerRepository iOfficerRepository;

	@MockBean
	private IScholarshipRepository iScholarshipRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAddOfficer() {
		//fail("Not yet implemented");
		Officer officer=getOfficer();
		Mockito.when(iOfficerRepository.save(Mockito.any(Officer.class))).thenReturn(officer);

		Officer resultOfficer=iOfficerService.addOfficer(officer);

		assertEquals(officer, resultOfficer);
	}

	@Test
	void testEditOfficer() {
		//fail("Not yet implemented");

		Officer officer=getOfficer();
		Optional<Officer> optional=Optional.of(officer);

		Mockito.when(iOfficerRepository.findById(officer.getUserId())).thenReturn(optional);
		Officer officerCopy=officer;
		officerCopy.setName("Ramesh");
		officerCopy.setPassword("offpass12" );
		Mockito.when(iOfficerRepository.save(Mockito.any(Officer.class))).thenReturn(officerCopy);

		Officer resultOfficer=iOfficerService.editOfficer(officerCopy);
		assertEquals(officerCopy, resultOfficer);
	}

	@Test
	void testGetOfficerByState() {
		//fail("Not yet implemented");
		Officer officer=getOfficer();
		Mockito.when(iOfficerRepository.fetchOfficerByState(officer.getState())).thenReturn(officer);

		Officer resultOfficer=iOfficerService.getOfficerByState("telangana");
		assertEquals(officer, resultOfficer);

		Officer resultOfficerOne=iOfficerService.getOfficerByState("Andhra");
		assertNotEquals(officer, resultOfficerOne);
	}

	@Test
	void testGetAllOfficer() {
		//fail("Not yet implemented");

		List<Officer> officerList=new ArrayList<Officer>();
		Officer officer=getOfficer();

		officerList.add(officer);
		Mockito.when(iOfficerRepository.findAll()).thenReturn(officerList);

		List<Officer> resultList=iOfficerService.getAllOfficer();

		assertEquals(officerList, resultList);	
	}

	@Test
	void testReviewInstitutes() {
		
		Institution institute=getInstitute();
		List<Institution> instituteList=new ArrayList<Institution>();
		instituteList.add(institute);
		
		List<Institution> resultList=iOfficerService.reviewInstitutes(instituteList);
		assertEquals("Approved", resultList.get(0).getStatus());
		
		institute.setCategory("public");
		List<Institution> resultListTwo=iOfficerService.reviewInstitutes(instituteList);
		assertNotEquals("Approved", resultListTwo.get(0).getStatus());
		
		assertEquals("Rejected", resultListTwo.get(0).getStatus());
		
		
		

	}

	@Test
	void testReviewScholarships() {
		
		Scholarship scholarship=getscholarship();
		
		List<Scholarship> scholarshipsList=new ArrayList<Scholarship>();
		
		scholarshipsList.add(scholarship);
		Mockito.when(iScholarshipRepository.save(Mockito.any(Scholarship.class))).thenReturn(scholarship);
		List<Scholarship> resultScholarship=iOfficerService.reviewScholarships(scholarshipsList);
		assertEquals("Approved",resultScholarship.get(0).getAppStatus());
		
		scholarship.setSscScore(40);
		List<Scholarship> scholarshipListTwo=new ArrayList<Scholarship>();
		scholarshipListTwo.add(scholarship);
		List<Scholarship> resultScholarshipTwo=iOfficerService.reviewScholarships(scholarshipListTwo);
		assertEquals("Rejected",resultScholarshipTwo.get(0).getAppStatus());
		
		assertNotEquals("Approved",resultScholarshipTwo.get(0).getAppStatus());
		
		
		
		
		
	}

	
	private Officer getOfficer()
	{
		Officer officer=new Officer();
		officer.setUserId("off01");
		officer.setPassword("offpass1");
		officer.setRole("officer");
		officer.setName("rajesh");
		officer.setState("telangana");


		return officer;
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
		Student s=new Student();
		s.setUserId("std01");
		scholarship.setStudent(s);
		Institution institute=getInstitute();
		institute.setStatus("Approved");
		scholarship.setInstitute(institute);
		
		
		return scholarship;
	}

}
