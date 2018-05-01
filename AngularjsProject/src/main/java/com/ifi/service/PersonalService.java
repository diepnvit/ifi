package com.ifi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.entity.Personal;
import com.ifi.repository.PersonalRepository;

@Service
@Transactional
public class PersonalService {
	@Autowired
	private PersonalRepository personalRepository;
	
	public List<Personal> getAllPersonal(){
		return personalRepository.findAll();
	}
	public Personal getPersonalById(int id) {
		return personalRepository.getOne(id);
	}
	
	public boolean addPersonal(Personal personal) {
		if(personalRepository.save(personal) != null) {
			return true;
		}
		return false;
	}
	
	public boolean updatePersonal(Personal personal) {
		if(personalRepository.saveAndFlush(personal) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deletePersonal(int id) {
		if(this.getPersonalById(id) != null) {
			personalRepository.delete(id);
			return true;
		}
		return false;
	}
}
