package com.ifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifi.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer>{

}
