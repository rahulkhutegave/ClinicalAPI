package com.clinicallab.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/emailcheck/{email}")
	public String emailCheck(@PathVariable String email) {
		//In frontEnd we will Implement AJAX 
		String regexPattern = "^(.+)@(\\S+)$";
		return service.emailCheck(email, regexPattern);
	}

	@PostMapping("/save")
	public String addPatient(@RequestBody PatientForm patient) throws PatientNotFoundException {

		boolean istrue = service.savePatient(patient);
		if (istrue) {
			return "Data Saved Successfully!!";
		} else
			return "Something went Wrong!!";
	}
	
	@GetMapping("/listpatients/{pageNo}")
	public List<Patient> listOfAllPatients(@PathVariable int pageNo){
		int size =5;
		return service.getPatients(pageNo, size);
	}
	
	@GetMapping("/sortedPatients/{pageNo}")
	public List<Patient> listOfPatientsInAgeOrder(@PathVariable int pageNo){
		return service.sortPatientsByAge(pageNo);
	}

}
