package com.cg.nsa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.repository.IUserDetailsRepository;
import com.cg.nsa.entity.UserDetails;

@Service
public class UserDetailsService implements IUserDetailsService {

	@Autowired
	private IUserDetailsRepository iUserDetailsRepository;

	@Override
	public UserDetails login(UserDetails userDetails) {

		//userId password role
		UserDetails fetchedUser = null;

		
			if("EducationMinister".equalsIgnoreCase(userDetails.getUserId()) && "min@123".equals(userDetails.getPassword())) {
				userDetails.setRole("ministry");
				fetchedUser = userDetails;
			}
		
		else {
//			fetchedUser = iUserDetailsRepository.findById(userDetails.getUserId()).get();
			fetchedUser = iUserDetailsRepository.login(userDetails.getUserId());
			if(fetchedUser!=null)
			{
				if(userDetails.getPassword().equals(fetchedUser.getPassword()))
				{
				}
				else
				{
					fetchedUser=null;
				}	
			}
		}
		return fetchedUser;
	}

	@Override
	public UserDetails logout(UserDetails userDetails) {
		return null;
	}

}
