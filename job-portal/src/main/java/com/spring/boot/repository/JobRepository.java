package com.spring.boot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Job;
@Repository
public interface JobRepository  extends JpaRepository<Job, Long>{
	  List<Job> findByTitleContainingIgnoreCase(String title);
	 List<Job> findByLocationContainingIgnoreCase(String location);
	
	 List<Job> findBySalaryGreaterThanEqual(Double salary);
	//public  Job findByJobId(Long id);
	List<Job> findAll();
	 List<Job> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
}
