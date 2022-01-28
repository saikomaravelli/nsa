package com.cg.nsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.nsa.entity.UserDetails;

@Repository
public interface IUserDetailsRepository extends JpaRepository<UserDetails,String> {
	
	@Query("select u from UserDetails u where u.userId=:id")
	UserDetails login(@Param("id") String userId);

}
