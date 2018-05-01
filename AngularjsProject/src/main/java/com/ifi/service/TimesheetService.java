package com.ifi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.entity.Project;
import com.ifi.entity.Timesheet;
import com.ifi.repository.ProjectRepository;
import com.ifi.repository.TimesheetRepository;


@Service
@Transactional
public class TimesheetService {
	@Autowired
	private TimesheetRepository timesheetRepository;
	
	public List<Timesheet> getAllTimesheet(){
		return timesheetRepository.findAll();
	}
	public Timesheet getTimesheetById(int id) {
		return timesheetRepository.getOne(id);
	}
	
	public boolean addTimesheet(Timesheet timesheet) {
		if(timesheetRepository.save(timesheet) != null) {
			return true;
		}
		return false;
	}
	
	public boolean updateTimesheet(Timesheet timesheet) {
		if(timesheetRepository.saveAndFlush(timesheet) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTimesheet(int id) {
		if(this.getTimesheetById(id) != null) {
			timesheetRepository.delete(id);
			return true;
		}
		return false;
	}
}
