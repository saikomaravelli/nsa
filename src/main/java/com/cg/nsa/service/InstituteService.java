package com.cg.nsa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.nsa.entity.Institution;
import com.cg.nsa.exception.InvalidInstitutionException;
import com.cg.nsa.repository.IInstituteRepository;

@Service
public class InstituteService implements IInstituteService {

	@Autowired
	private IInstituteRepository iInstituteRepository;
	@Override
	public Institution addInstitute(Institution institute) {
		
		institute.setStatus("Pending");
		return iInstituteRepository.save(institute);
	
	}

	@Override
	public Institution editInstitute(Institution institute) {
		Institution inst = iInstituteRepository.findById(institute.getUserId()).get();
		if(inst != null) {
			if(institute.getPassword() != null) {
				inst.setPassword(institute.getPassword());
			}
			if(institute.getCode() > 0) {
				inst.setCode(institute.getCode());
			}
			if(institute.getCategory() != null) {
				inst.setCategory(institute.getCategory());
			}
			if(institute.getType() != null) {
				inst.setType(institute.getType());
			}
			if(institute.getName() != null) {
				inst.setName(institute.getName());
			}
			if(institute.getUniversity() != null) {
				inst.setUniversity(institute.getUniversity());
			}
			if(institute.getAddress() != null) {
				inst.setAddress(institute.getAddress());
			}
			if(institute.getCity() != null) {
				inst.setCity(institute.getCity());
			}
			if(institute.getState() != null) {
				inst.setState(institute.getState());
			}
			if(institute.getYearOpen() > 0) {
				inst.setYearOpen(institute.getYearOpen());
			}
			if(institute.getTelephone() != null) {
				inst.setTelephone(institute.getTelephone());
			}
			if(institute.getPrincipal() != null) {
				inst.setPrincipal(institute.getPrincipal());
			}
			if(institute.getStatus() != null) {
				inst.setStatus(institute.getStatus());
			}
			return iInstituteRepository.save(inst);
		}
		return inst;
	}

	@Override
	public Institution statusUpdate(Institution institute) {
		// TODO Auto-generated method stub
		Institution institution = iInstituteRepository.findById(institute.getUserId()).get();  
		return institution;
	}

	@Override
	public Institution getInstitute(int code) throws InvalidInstitutionException {
		// TODO Auto-generated method stub
		Institution institution = iInstituteRepository.fetchInstitute(code);
		
		if(institution == null) {
			throw new InvalidInstitutionException("institution is not available");
		}
		return institution;
	}

	@Override
	public List<Institution> getAllInstitutes() {
		// TODO Auto-generated method stub
		return iInstituteRepository.findAll();
	}

	@Override
	public List<Institution> getInstitutesByState(String state) {
		// TODO Auto-generated method stub
		return iInstituteRepository.fetchInstitutesByState(state);
	}

}
