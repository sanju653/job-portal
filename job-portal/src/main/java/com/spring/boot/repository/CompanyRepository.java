package com.spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long>{
	  public Company findByEmail(String email);

	    // 🔹 Prevent duplicate company registration
	  public boolean existsByEmail(String email);

	    // 🔹 Search company by name
	  public   List<Company> findByNameContainingIgnoreCase(String name);
	  //public   List<Company> findByLocationContainingIgnoreCase(String location);

}
