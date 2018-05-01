package com.ifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifi.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
