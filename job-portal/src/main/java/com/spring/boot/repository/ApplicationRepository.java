package com.spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {


 public List<Application> findByApplicantId(Long applicantId);

  
 public List<Application> findByJobId(Long jobId);

    public boolean existsByApplicantIdAndJobId(Long applicantId, Long jobId);
}

