package com.clinicallab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicallab.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
