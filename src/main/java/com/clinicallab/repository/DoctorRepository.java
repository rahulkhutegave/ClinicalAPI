package com.clinicallab.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicallab.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Serializable> {

}
