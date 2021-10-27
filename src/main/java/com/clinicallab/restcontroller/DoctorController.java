package com.clinicallab.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallab.exception.DoctorNotFoundException;
import com.clinicallab.model.Doctor;
import com.clinicallab.service.ClinicalService;

@RestController
public class DoctorController {

	@Autowired
	private ClinicalService service;

	@PostMapping("/savedoctor")
	public String addDoctor(@RequestBody Doctor doctor) throws DoctorNotFoundException {

		boolean istrue = service.saveDoctor(doctor);
		if (istrue) {
			return "Data Saved Successfully!!";
		} else
			return "Something went Wrong!!";

	}

}
