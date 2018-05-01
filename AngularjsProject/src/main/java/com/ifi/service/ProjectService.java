package com.ifi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifi.entity.Project;
import com.ifi.repository.ProjectRepository;


@Service
@Transactional
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> getAllProject(){
		return projectRepository.findAll();
	}
	public Project getProjectById(int id) {
		return projectRepository.getOne(id);
	}
	
	public boolean addProject(Project project) {
		if(projectRepository.save(project) != null) {
			return true;
		}
		return false;
	}
	
	public boolean updateProject(Project project) {
		if(projectRepository.saveAndFlush(project) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteProject(int id) {
		if(this.getProjectById(id) != null) {
			projectRepository.delete(id);
			return true;
		}
		return false;
	}
}
