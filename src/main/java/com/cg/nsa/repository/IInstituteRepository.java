package com.cg.nsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.exception.InvalidInstitutionException;
@Repository
public interface IInstituteRepository extends JpaRepository<Institution, String>{

	
	@Query("select f from com.cg.nsa.entity.Institution f where f.code=:cd")
	Institution fetchInstitute(@Param("cd") int code) throws InvalidInstitutionException;
	
	
	@Query("select f from com.cg.nsa.entity.Institution f where f.state=:st")
	List<Institution> fetchInstitutesByState(@Param("st") String state);
}
