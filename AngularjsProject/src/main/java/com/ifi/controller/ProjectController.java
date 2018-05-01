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

import com.ifi.entity.Project;
import com.ifi.service.ProjectService;

@RestController
@RequestMapping(value = "/api")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/project/")
	public ResponseEntity<List<Project>> getAllProject() {
		List<Project> lst = projectService.getAllProject();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<Project>>(lst, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Project>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/project/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
		Project project = projectService.getProjectById(id);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}

	@PostMapping(value = "/project/")
	public ResponseEntity<?> addPersonal(@RequestBody Project project, UriComponentsBuilder builder) {
		boolean flag = projectService.addProject(project);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/project/{id}").buildAndExpand(project.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/project/{id}")
	public ResponseEntity<?> updatePersonal(@PathVariable("id") int id,@RequestBody Project project) {
		Project project2 = projectService.getProjectById(id);
		project2.setName(project.getName());
		project2.setEndDate(project.getEndDate());
		project2.setStartDate(project.getStartDate());
		projectService.updateProject(project2);
		return new ResponseEntity<Project>(project2, HttpStatus.OK);
	}

	@DeleteMapping(value = "/project/{id}")
	public ResponseEntity<?> deletePersonal(@PathVariable("id") int id) {
		projectService.deleteProject(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
