package com.cg.nsa.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.nsa.entity.Scholarship;
@Repository
public interface IScholarshipRepository extends JpaRepository<Scholarship, Integer>{
	@Query("select s from Scholarship s where s.student.userId=:id")
	List<Scholarship> statusUpdate(@Param("id") String userId);
	
	@Query("select s from Scholarship s where s.institute.state=:state")
	List<Scholarship> getScholarshipsByState(@Param("state") String state);
}
