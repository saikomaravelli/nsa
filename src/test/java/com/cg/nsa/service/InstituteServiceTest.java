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
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.repository.IInstituteRepository;

@ContextConfiguration(classes = NsaApplication.class)
@WebMvcTest(value = InstituteService.class)
class InstituteServiceTest {

	@Autowired
	private IInstituteService iInstituteService;
	
	@MockBean
	private IInstituteRepository iInstituteRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAddInstitute() {
		Institution institute = getInstitute();
		
		Mockito.when(iInstituteRepository.save(Mockito.any(Institution.class))).thenReturn(institute);
		
		Institution resultInstitute = iInstituteService.addInstitute(institute);
		assertEquals(institute, resultInstitute);
	}

	@Test
	void testEditInstitute() {
		Institution institute = getInstitute();
		Optional<Institution> o = Optional.of(institute);
		
		Mockito.when(iInstituteRepository.findById(institute.getUserId())).thenReturn(o);
		
		Institution instituteCopy = institute;
		instituteCopy.setYearOpen(2000);
		instituteCopy.setPassword("change123");
		Mockito.when(iInstituteRepository.save(Mockito.any(Institution.class))).thenReturn(instituteCopy);
		
		Institution resultInstitute = iInstituteService.editInstitute(instituteCopy);
		assertEquals(instituteCopy, resultInstitute);
	}

	@Test
	void testStatusUpdate() {
		//fail("Not yet implemented");
		Institution institute = getInstitute();
		Optional<Institution> o = Optional.of(institute);
		
		Mockito.when(iInstituteRepository.findById(institute.getUserId())).thenReturn(o);
		
		Institution resultInstitute = iInstituteService.statusUpdate(institute);
		assertEquals(institute, resultInstitute);	
		
		
	}

	@Test
	void testGetInstitute() throws InvalidInstitutionException {
		//fail("Not yet implemented");
		
		Institution institute = getInstitute();
		Mockito.when(iInstituteRepository.fetchInstitute(institute.getCode())).thenReturn(institute);
		
		Institution resultInstitute = iInstituteService.getInstitute(123);
		assertEquals(institute, resultInstitute);
		
		try {
		Institution resultInstituteTwo = iInstituteService.getInstitute(456);
		assertEquals(institute, resultInstitute);
		}
		catch(InvalidInstitutionException e){
			assertEquals("institution is not available",e.getMessage());
		}
		
	}

	@Test
	void testGetAllInstitutes() {
		//fail("Not yet implemented");
		List<Institution> instituteList = new ArrayList<Institution>();
		Institution institute = getInstitute();
		
		instituteList.add(institute);
		Mockito.when(iInstituteRepository.findAll()).thenReturn(instituteList);
		
		List<Institution> resultList = iInstituteService.getAllInstitutes();
		
		assertEquals(instituteList, resultList);
		
	}

	@Test
	void testGetInstitutesByState() {
		List<Institution> instituteList = new ArrayList<Institution>();
		Institution institute = getInstitute();
		instituteList.add(institute);
		
		Mockito.when(iInstituteRepository.fetchInstitutesByState(institute.getState())).thenReturn(instituteList);
		
		List<Institution> resultList = iInstituteService.getInstitutesByState("Telangana");
		assertEquals(instituteList, resultList);
		
		List<Institution> resultListTwo = iInstituteService.getInstitutesByState("AndhraPradesh");
		assertNotEquals(instituteList, resultListTwo);
		
		
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

}
