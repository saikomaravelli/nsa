package com.cg.nsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.nsa.entity.UserDetails;
import com.cg.nsa.service.IUserDetailsService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserDetailsController {
	
	@Autowired
	private IUserDetailsService iUserDetailsService;

	@PutMapping("userLogin")
	public UserDetails login(@RequestBody UserDetails userDetails) {
		UserDetails userLoginDetails=iUserDetailsService.login(userDetails);
		return userLoginDetails;

	}
	@PutMapping("userLogout")
	public UserDetails logout(@RequestBody UserDetails userDetails) {
		UserDetails userLogoutDetails=iUserDetailsService.logout(userDetails);
		return userLogoutDetails;
	}
}