package com.cg.nsa.service;

import static org.junit.jupiter.api.Assertions.*;

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
import com.cg.nsa.entity.UserDetails;
import com.cg.nsa.repository.IUserDetailsRepository;

@ContextConfiguration(classes=NsaApplication.class)
@WebMvcTest(value=UserDetailsService.class)
class UserDetailsServiceTest {
	
	@Autowired
	private IUserDetailsService iUserDetailsService;
	
	@MockBean
	private IUserDetailsRepository iUserDetailsRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	

	@Test
	void testLogin() {
		
		UserDetails userDetails=getUserDetails();
		Optional<UserDetails> optional = Optional.of(userDetails);
		Mockito.when(iUserDetailsRepository.findById(userDetails.getUserId())).thenReturn(optional);
		
		UserDetails resultDetails = iUserDetailsService.login(userDetails);
		assertEquals(userDetails, resultDetails);
		
		
		UserDetails ministryDetails = new UserDetails();
		ministryDetails.setUserId("EducationMinister");
		ministryDetails.setPassword("min@123");
		ministryDetails.setRole("ministry");
		UserDetails resultMinistryDetails = iUserDetailsService.login(ministryDetails);
		assertEquals(ministryDetails, resultMinistryDetails);
		
		
	}

	@Test
	void testLogout() {
		
		UserDetails userDetails = getUserDetails();
		UserDetails returnUserDetails = iUserDetailsService.logout(userDetails);
		assertNull(returnUserDetails);
		
	}
	
	private UserDetails getUserDetails()
	{
		UserDetails userDetails=new UserDetails();
		
		userDetails.setUserId("user01");
		userDetails.setPassword("userPass1");
		userDetails.setRole("officer");
		
		return userDetails;
	}
	

}
