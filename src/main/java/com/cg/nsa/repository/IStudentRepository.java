package com.cg.nsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.nsa.entity.Student;
import com.cg.nsa.exception.InvalidInstitutionException;

@Repository
public interface IStudentRepository extends JpaRepository<Student, String>{

	@Query("select s from com.cg.nsa.entity.Student s JOIN com.cg.nsa.entity.Scholarship sc ON "
			+ "(s.userId=sc.student.userId) JOIN com.cg.nsa.entity.Institution i ON "
			+ "(sc.institute.userId=i.userId and i.name=:nm)") 
	List<Student> fetchStudentsByInstitute(@Param("nm") String name) throws InvalidInstitutionException;
	
	@Query("select s from com.cg.nsa.entity.Student s where s.userId=:uid")
	Student fetchStudentByUserId(@Param("uid") String userId);



}
