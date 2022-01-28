package com.cg.nsa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.nsa.entity.Officer;

@Repository
public interface IOfficerRepository extends JpaRepository<Officer, String> {

	@Query("select f from com.cg.nsa.entity.Officer f where f.state=:st")
	Officer fetchOfficerByState(@Param("st") String state);


}
