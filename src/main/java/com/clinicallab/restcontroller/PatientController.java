package com.clinicallab.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallab.bindings.PatientForm;
import com.clinicallab.exception.PatientNotFoundException;
import com.clinicallab.model.Patient;
import com.clinicallab.service.ClinicalService;

@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private ClinicalService service;

	@PostMapping("/save")
	public String addPatient(@RequestBody PatientForm patient) throws PatientNotFoundException {

		boolean istrue = service.savePatient(patient);
		if (istrue) {
			return "Data Saved Successfully!!";
		} else
			return "Something went Wrong!!";
	}
	
	@GetMapping("/listpatients")
	public List<Patient> listOfAllPatients(){
		
		return service.getPatients();
	}

}
