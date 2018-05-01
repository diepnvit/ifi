package com.ifi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifi.entity.Personal;
import com.ifi.service.PersonalService;

@RestController
@RequestMapping(value = "/api")
public class PersonalController {
	@Autowired
	private PersonalService personalService;
	
	@GetMapping(value = "/personal/")
	public ResponseEntity<List<Personal>> getAllPersonal(){
		List<Personal> lst = personalService.getAllPersonal();
		if(lst.isEmpty()) {
			return new ResponseEntity<List<Personal>>(lst, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Personal>>(lst, HttpStatus.OK);
	}
	@GetMapping(value = "/personal/{id}")
	public ResponseEntity<Personal> getPersonalById(@PathVariable("id") int id){
		Personal personal = personalService.getPersonalById(id);
		return new ResponseEntity<>(personal, HttpStatus.OK);
	}
	@PostMapping(value = "/personal/")
	public ResponseEntity<?> addPersonal(@RequestBody Personal personal, UriComponentsBuilder builder){
		boolean flag= personalService.addPersonal(personal);
		if(flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/personal/{id}").buildAndExpand(personal.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/personal/{id}")
	public ResponseEntity<?> updatePersonal(@PathVariable("id") int id,@RequestBody Personal personal){
		Personal personal2 = personalService.getPersonalById(id);
		personal2.setAddress(personal.getAddress());
		personal2.setDob(personal.getDob());
		personal2.setFullname(personal.getFullname());
		personal2.setGender(personal.getGender());
		personal2.setPhone(personal.getPhone());
		personalService.updatePersonal(personal2);
		return new ResponseEntity<Personal>(personal2, HttpStatus.OK);
	}
	@DeleteMapping(value = "/personal/{id}")
	public ResponseEntity<?> deletePersonal(@PathVariable("id") int id){
		personalService.deletePersonal(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
