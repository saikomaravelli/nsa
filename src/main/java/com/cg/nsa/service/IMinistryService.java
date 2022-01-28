package com.cg.nsa.service;

import java.util.List;

import com.cg.nsa.entity.Scholarship;

public interface IMinistryService {
	List<Scholarship> grant(List<Scholarship> scholarshipList);

}
