package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	public Applicant findByEmail(String email);
	public boolean existsByEmail(String email);

}
