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
import com.ifi.entity.Project;
import com.ifi.entity.Timesheet;
import com.ifi.service.PersonalService;
import com.ifi.service.ProjectService;
import com.ifi.service.TimesheetService;

@RestController
@RequestMapping(value = "/api")
public class TimesheetController {
	@Autowired
	private TimesheetService timesheetService;
	@Autowired
	PersonalService personalService;
	@Autowired
	ProjectService projectService;
	
	@GetMapping(value = "/timesheet/")
	public ResponseEntity<List<Timesheet>> getAllTimesheet(){
		List<Timesheet> lst = timesheetService.getAllTimesheet();
		if(lst.isEmpty()) {
			return new ResponseEntity<List<Timesheet>>(lst, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Timesheet>>(lst, HttpStatus.OK);
	}
	@GetMapping(value = "/timesheet/{id}")
	public ResponseEntity<Timesheet> getTimesheetById(@PathVariable("id") int id){
		Timesheet timesheet = timesheetService.getTimesheetById(id);
		return new ResponseEntity<>(timesheet, HttpStatus.OK);
	}
	@PostMapping(value = "/timesheet/")
	public ResponseEntity<?> addTimesheet(@RequestBody Timesheet timesheet, UriComponentsBuilder builder){
		int personal = timesheet.getPersonal().getId();
		int project = timesheet.getProject().getId();
		Timesheet timesheet2 = new Timesheet(timesheet.getLocation(),timesheet.getWorkday(),personalService.getPersonalById(personal),projectService.getProjectById(project));
		boolean flag= timesheetService.addTimesheet(timesheet2);
		if(flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/timesheet/{id}").buildAndExpand(timesheet2.getIdTimesheet()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/timesheet/{id}")
	public ResponseEntity<?> updateTimesheet(@PathVariable("id") int id,@RequestBody Timesheet timesheet){
		int personal = timesheet.getPersonal().getId();
		int project = timesheet.getProject().getId();
		Timesheet timesheet2 = timesheetService.getTimesheetById(id);
		timesheet2 =  new Timesheet(timesheet.getIdTimesheet(),timesheet.getLocation(),timesheet.getWorkday(),personalService.getPersonalById(personal),projectService.getProjectById(project));
		timesheetService.updateTimesheet(timesheet2);
		return new ResponseEntity<Timesheet>(timesheet2, HttpStatus.OK);
	}
	@DeleteMapping(value = "/timesheet/{id}")
	public ResponseEntity<?> deleteTimesheet(@PathVariable("id") int id){
		timesheetService.deleteTimesheet(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
