package com.cg.nsa.service;

import com.cg.nsa.entity.UserDetails;

public interface IUserDetailsService {

	UserDetails login(UserDetails userDetails);

	UserDetails logout(UserDetails userDetails);

}
