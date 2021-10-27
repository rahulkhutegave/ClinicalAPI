package com.clinicallab.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallab.bindings.AppointmentForm;
import com.clinicallab.exception.ClinicalException;
import com.clinicallab.model.Appointment;
import com.clinicallab.service.ClinicalService;

@RestController
public class AppointmentController {

	@Autowired
	private ClinicalService service;

	@PostMapping("/saveappt")
	public String giveAppointment(@RequestBody AppointmentForm appt) throws ClinicalException {

		boolean istrue = service.addAppointmentDetails(appt);
		if (istrue) {
			return "Appointment Generated Successfully!";
		} else {
			return "Something Went wrong";
		}
	}

}
