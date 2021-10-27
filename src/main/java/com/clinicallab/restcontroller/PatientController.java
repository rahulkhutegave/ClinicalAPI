package com.clinicallab.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallab.bindings.PatientForm;
import com.clinicallab.exception.PatientNotFoundException;
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

}
