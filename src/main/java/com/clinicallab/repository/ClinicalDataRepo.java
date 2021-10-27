package com.clinicallab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicallab.model.ClinicalData;

@Repository
public interface ClinicalDataRepo extends JpaRepository<ClinicalData, Integer> {

}
