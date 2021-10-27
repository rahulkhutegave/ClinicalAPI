package com.clinicallab.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallab.bindings.ClinicalDataRequest;
import com.clinicallab.exception.PatientNotFoundException;
import com.clinicallab.model.ClinicalData;
import com.clinicallab.service.ClinicalService;

@RestController
public class ClinicalDataController {

	@Autowired
	private ClinicalService service;

	@PostMapping("/clinicals")
	public String addClinicalData(@RequestBody ClinicalDataRequest data) throws PatientNotFoundException {

		boolean istrue = service.saveClinicalData(data);
		if(istrue) {
			return "Data Saved Successfully!!";
		}else {
			return "Something went Wrong!!";
		}
	}

}
